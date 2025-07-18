package com.ainal.apps.wise_spends.security.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.ref.UserRole;
import com.ainal.apps.wise_spends.common.domain.usr.Credential;
import com.ainal.apps.wise_spends.common.domain.usr.Individual;
import com.ainal.apps.wise_spends.common.domain.usr.Role;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.ref.IUserRoleRepository;
import com.ainal.apps.wise_spends.common.repository.usr.ICredentialRepository;
import com.ainal.apps.wise_spends.common.repository.usr.IIndividualRepository;
import com.ainal.apps.wise_spends.common.repository.usr.IRoleRepository;
import com.ainal.apps.wise_spends.common.repository.usr.IUserRepository;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.security.config.service.IJwtService;
import com.ainal.apps.wise_spends.security.config.service.IUserJwtViewObjectService;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ICredentialRepository credentialRepository;

	@Autowired
	private IUserRoleRepository userRoleRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private IIndividualRepository individualRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IJwtService jwtService;

	@Autowired
	private IUserJwtViewObjectService userJwtViewObjectService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IBaseManager baseManager;

	public AuthenticationResponse register(RegisterRequest request) {
		Individual individual = new Individual();
		individual.setFirstName(request.getFirstName());
		individual.setLastName(request.getLastName());
		individual.setNickName(request.getNickName());
		individual.setGender(request.getGender());
		individual.setEmail(request.getEmail());
		baseManager.setBaseEntityAttributes(individual,
				request.getFlagUsernameIsEmail() ? individual.getEmail() : request.getUsername());
		individual = individualRepository.saveAndFlush(individual);

		Credential credential = new Credential();
		credential.setUsername(request.getUsername());
		credential.setFlagUsernameIsEmail(request.getFlagUsernameIsEmail());
		credential.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
		baseManager.setBaseEntityAttributes(credential,
				credential.getFlagUsernameIsEmail() ? individual.getEmail() : credential.getUsername());
		credential = credentialRepository.saveAndFlush(credential);

		User user = new User();
		user.setCredential(credential);
		user.setIndividual(individual);
		baseManager.setBaseEntityAttributes(user,
				credential.getFlagUsernameIsEmail() ? individual.getEmail() : credential.getUsername());
		user = userRepository.saveAndFlush(user);

		UserRole userRole = userRoleRepository.findByCode("N_USR");
		if (userRole == null) {
			userRole = new UserRole();
			userRole.setCode("N_USR");
		}

		Role role = new Role();
		role.setUser(user);
		role.setFlagMainRole(Boolean.TRUE);
		role.setUserRole(userRole);
		baseManager.setBaseEntityAttributes(role,
				credential.getFlagUsernameIsEmail() ? individual.getEmail() : credential.getUsername());
		roleRepository.saveAndFlush(role);

		UserJwtViewObject userJwtViewObject = new UserJwtViewObject();
		userJwtViewObject
				.setUsernameOrEmail(request.getFlagUsernameIsEmail() ? request.getEmail() : request.getUsername());
		userJwtViewObject.setPassword(request.getPassword());
		userJwtViewObject
				.setGrantedAuthorities(List.of((GrantedAuthority) new SimpleGrantedAuthority(userRole.getCode())));
		userJwtViewObject.setName(request.getNickName());

		String jwtToken = jwtService.generateToken(userJwtViewObject);

		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setToken(jwtToken);
		return authenticationResponse;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));
		UserJwtViewObject userJwtViewObject = userJwtViewObjectService
				.loadUserJwtViewObjectByUsernameOrEmail(request.getUsernameOrEmail()).orElse(null);
		String jwtToken = jwtService.generateToken(userJwtViewObject);

		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setToken(jwtToken);
		return authenticationResponse;

	}
}
