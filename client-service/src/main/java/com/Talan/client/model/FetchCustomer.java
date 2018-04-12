package com.Talan.client.model;

public enum FetchCustomer {

	CustCode("CustCode", 1), Msisdn("Msisdn", 2), Contract_ID("Contract_ID", 3);
	private String label;
	private int code;

	private FetchCustomer(String label, int code) {
		this.label = label;
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public int getCode() {
		return code;
	}

}
