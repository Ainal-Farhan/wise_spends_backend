package com.ainal.apps.wise_spends.common.service.mny;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;

import io.micrometer.common.lang.NonNull;

public interface ICreditCardService {
	List<CreditCard> findCreditCardListByMoneyStorage(@NonNull MoneyStorage moneyStorage);

	List<CreditCard> findCreditCardListByUser(@NonNull User user);

	CreditCard findCreditCardById(Long creditCardId);

	CreditCard saveCreditCard(CreditCard creditCard);

	void deleteCreditCardById(Long creditCardId);
}
