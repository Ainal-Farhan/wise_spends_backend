package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.form.view.object.SavingFormVO;
import com.ainal.apps.wise_spends.view.object.SavingVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface ISavingManager {
	List<SavingVO> populateSavingVOList(@NonNull HttpServletRequest request);

	Saving getSavingById(@NonNull Long savingId);

	Saving addNewSavingForCurrentUser(@NonNull HttpServletRequest request, SavingFormVO savingFormVO);

	Saving updateSavingForCurrentUser(@NonNull HttpServletRequest request, SavingFormVO savingFormVO);

	boolean deleteSavingById(@NonNull Long savingId);
}
