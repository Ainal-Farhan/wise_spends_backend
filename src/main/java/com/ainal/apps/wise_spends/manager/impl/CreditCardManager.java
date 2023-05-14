package com.ainal.apps.wise_spends.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.mny.ICreditCardService;
import com.ainal.apps.wise_spends.form.view.object.CreditCardFormVO;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICreditCardManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.view.object.CreditCardVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class CreditCardManager implements ICreditCardManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	private IBaseManager baseManager;

	@Autowired
	private IMoneyStorageManager moneyStorageManager;

	@Autowired
	private ICreditCardService creditCardService;

	@Override
	public List<CreditCardVO> populateCreditCardVOList(@NonNull HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<CreditCard> cardCreditList = creditCardService.findCreditCardListByUser(currentUser);

		if (CollectionUtils.isEmpty(cardCreditList)) {
			return new ArrayList<>();
		}

		return cardCreditList.stream().map(cardCredit -> new CreditCardVO(cardCredit)).collect(Collectors.toList());
	}

	@Override
	public CreditCard getCreditCardById(Long creditCardId) {
		return creditCardService.findCreditCardById(creditCardId);
	}

	@Override
	public CreditCard addNewCreditCardForCurrentUser(@NonNull HttpServletRequest request,
			CreditCardFormVO creditCardFormVO) {
		if (creditCardFormVO == null) {
			return null;
		}

		CreditCard creditCard = new CreditCard();
		setCreditCardData(creditCard, creditCardFormVO, request);

		return creditCardService.saveCreditCard(creditCard);
	}

	@Override
	public CreditCard updateCreditCardForCurrentUser(@NonNull HttpServletRequest request,
			CreditCardFormVO creditCardFormVO) {
		if (creditCardFormVO == null) {
			return null;
		}

		CreditCard creditCard = creditCardService.findCreditCardById(creditCardFormVO.getId());
		setCreditCardData(creditCard, creditCardFormVO, request);

		return creditCardService.saveCreditCard(creditCard);
	}

	@Override
	public boolean deleteCreditCardById(Long creditCardId) {
		creditCardService.deleteCreditCardById(creditCardId);
		return true;
	}

	private void setCreditCardData(CreditCard creditCard, CreditCardFormVO creditCardFormVO,
			HttpServletRequest request) {
		if (creditCardFormVO == null || creditCard == null) {
			return;
		}

		baseManager.setBaseEntityAttributes(creditCard, request);
		if (creditCardFormVO.getMoneyStorageId() != null) {
			MoneyStorage moneyStorage = moneyStorageManager.getMoneyStorageById(creditCardFormVO.getMoneyStorageId());
			creditCard.setMoneyStorage(moneyStorage);
		}

		creditCard.setShortName(creditCardFormVO.getShortName());
		creditCard.setFullName(creditCardFormVO.getFullName());
		creditCard.setAbbreviation(creditCardFormVO.getAbbreviation());
		creditCard.setCreditAmount(creditCardFormVO.getCreditAmount() == null ? BigDecimal.valueOf(0)
				: creditCardFormVO.getCreditAmount());

	}

}
