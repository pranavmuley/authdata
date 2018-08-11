/**
 * 
 */
package com.example.authdata;;

/**
 * @author rajeshsaw
 *
 */
public class MerchantAuthDataResponse {
	
	private String emailId;
	private MerchantAuthDataAccess merchantAuthDataAccess;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public MerchantAuthDataAccess getMerchantAuthDataAccess() {
		return merchantAuthDataAccess;
	}
	public void setMerchantAuthDataAccess(MerchantAuthDataAccess merchantAuthDataAccess) {
		this.merchantAuthDataAccess = merchantAuthDataAccess;
	}
	@Override
	public String toString() {
		return "MerchantAuthDataResponse [emailId=" + emailId + ", merchantAuthDataAccess=" + merchantAuthDataAccess
				+ "]";
	} 
}
