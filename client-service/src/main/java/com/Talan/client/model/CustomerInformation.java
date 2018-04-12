package com.Talan.client.model;


public class CustomerInformation {

	private String custCode;
	private String paymentResp;
	private String dummy;
	private String reason;
	private String cin;
	private String companyRgister;
	private String fName;
	private String lName;
	private String errorMsg;
	private String errorCode;
	

	public CustomerInformation() {
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getPaymentResp() {
		return paymentResp;
	}

	public void setPaymentResp(String paymentResp) {
		this.paymentResp = paymentResp;
	}

	public String getDummy() {
		return dummy;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCompanyRgister() {
		return companyRgister;
	}

	public void setCompanyRgister(String companyRgister) {
		this.companyRgister = companyRgister;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "CustomerInformation [custCode=" + custCode + ", paymentResp=" + paymentResp + ", dummy=" + dummy
				+ ", reason=" + reason + ", cin=" + cin + ", companyRgister=" + companyRgister + ", fName=" + fName
				+ ", lName=" + lName + ", errorMsg=" + errorMsg + ", errorCode=" + errorCode + "]";
	}

}
