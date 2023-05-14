package com.ainal.apps.wise_spends.common.service.impl.mny;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.mny.ICreditCardRepository;
import com.ainal.apps.wise_spends.common.service.mny.ICreditCardService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreditCardService implements ICreditCardService {
	@Autowired
	ICreditCardRepository creditCardRepository;

	@Override
	public List<CreditCard> findCreditCardListByMoneyStorage(MoneyStorage moneyStorage) {
		return creditCardRepository.findByMoneyStorage(moneyStorage);
	}

	@Override
	public List<CreditCard> findCreditCardListByUser(User user) {
		return creditCardRepository.findByUser(user);
	}

	@Override
	public CreditCard findCreditCardById(Long creditCardId) {
		return creditCardRepository.findById(creditCardId).orElse(null);
	}

	@Override
	public CreditCard saveCreditCard(CreditCard creditCard) {
		return creditCardRepository.save(creditCard);
	}

	@Override
	public void deleteCreditCardById(Long creditCardId) {
		creditCardRepository.deleteById(creditCardId);
	}

}
