package com.ainal.apps.wise_spends.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.manager.ISavingManager;
import com.ainal.apps.wise_spends.view.object.form.SavingFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/saving")
public class SavingRestController {
	@Autowired
	private ISavingManager savingManager;

	@PostMapping("")
	public RedirectView addSaving(@ModelAttribute(name = "savingFormVO") SavingFormVO savingFormVO,
			@NonNull HttpServletRequest request) {
		if (savingFormVO.getId() != null) {
			savingManager.updateSavingForCurrentUser(request, savingFormVO);
		} else {
			savingManager.addNewSavingForCurrentUser(request, savingFormVO);
		}
		return new RedirectView("/saving");
	}

	@DeleteMapping("/{savingId}")
	public ResponseEntity<String> deleteSaving(@PathVariable("savingId") Long savingId) {
		savingManager.deleteSavingById(savingId);

		return ResponseEntity.ok("Item deleted successfully");
	}

}
