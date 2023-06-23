package com.ainal.apps.wise_spends.reference.api.rest;

public interface RestApiReference {
	String BASE_ENDPOINT = "/api";

	String ENDPOINT_CREDIT_CARD = BASE_ENDPOINT + "/credit_card";
	String ENDPOINT_MONEY_STORAGE = BASE_ENDPOINT + "/money_storage";
	String ENDPOINT_MONEY_TRANSACTION_CATEGORY_REFERENCE = BASE_ENDPOINT + "/transaction_category_reference";
	String ENDPOINT_MONEY_TRANSACTION_REFERENCE = BASE_ENDPOINT + "/transaction_reference";
	String ENDPOINT_MONEY_TRANSACTION = BASE_ENDPOINT + "/transaction";
	String ENDPOINT_SAVING = BASE_ENDPOINT + "/saving";
}
