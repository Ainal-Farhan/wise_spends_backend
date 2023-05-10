package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface IMoneyStorageManager {
	List<MoneyStorageVO> populateMoneyStorageVOList(@NonNull HttpServletRequest request);
}
