package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.form.view.object.CreditCardFormVO;
import com.ainal.apps.wise_spends.view.object.CreditCardVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface ICreditCardManager {
	List<CreditCardVO> populateCreditCardVOList(@NonNull HttpServletRequest request);

	CreditCard getCreditCardById(@NonNull Long creditCardId);

	CreditCard addNewCreditCardForCurrentUser(@NonNull HttpServletRequest request, CreditCardFormVO creditCardFormVO);

	CreditCard updateCreditCardForCurrentUser(@NonNull HttpServletRequest request, CreditCardFormVO creditCardFormVO);

	boolean deleteCreditCardById(@NonNull Long creditCardId);

}
