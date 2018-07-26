/**
 * 
 */
package com.example.authdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author rajeshsaw
 *
 */
@Service
public class AuthorizationDataServiceManager {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public ResponseEntity<MerchantAuthDataResponse> getAuthData(String emailId) {
		
		if(!validate(emailId)) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		MerchantAuthDataResponse merchantAuthDataResponse = new MerchantAuthDataResponse();
		merchantAuthDataResponse.setEmailId(emailId);
		MerchantAuthDataAccess merchantAuthDataAccess = null;
		
		if(emailId.equalsIgnoreCase("limitedTransactions@globalpay.com")) {
			merchantAuthDataAccess = new MerchantAuthDataAccess();
			merchantAuthDataAccess.setChargeback(false);
			merchantAuthDataAccess.setAdmin(false);
			merchantAuthDataAccess.setManageAccount(false);
			merchantAuthDataAccess.setTransactions(true);
		}		
		else if(emailId.equalsIgnoreCase("chargeback@globalpay.com")) {
			merchantAuthDataAccess = new MerchantAuthDataAccess();
			merchantAuthDataAccess.setChargeback(true);
			merchantAuthDataAccess.setAdmin(false);
			merchantAuthDataAccess.setManageAccount(false);
			merchantAuthDataAccess.setTransactions(false);
		}
		
		else if(emailId.equalsIgnoreCase("blacklisted@globalpay.com")) {
			merchantAuthDataAccess = new MerchantAuthDataAccess();
			merchantAuthDataAccess.setChargeback(false);
			merchantAuthDataAccess.setAdmin(false);
			merchantAuthDataAccess.setManageAccount(false);
			merchantAuthDataAccess.setTransactions(false);
		}
		else {
			merchantAuthDataAccess = new MerchantAuthDataAccess();
			merchantAuthDataAccess.setChargeback(true);
			merchantAuthDataAccess.setAdmin(true);
			merchantAuthDataAccess.setManageAccount(true);
			merchantAuthDataAccess.setTransactions(true);
		}
		
		merchantAuthDataResponse.setMerchantAuthDataAccess(merchantAuthDataAccess);
		return new ResponseEntity<>(merchantAuthDataResponse, HttpStatus.OK);
	}
	private boolean validate(String emailAddress) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailAddress);
        return matcher.find();
	}
}
