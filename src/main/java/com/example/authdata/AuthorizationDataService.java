/**
 * 
 */
package com.example.authdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rajeshsaw
 *
 */
@RestController
public class AuthorizationDataService {
	@Autowired
	AuthorizationDataServiceManager authorizationDataServiceManager;
	@RequestMapping("/authdata")
    public ResponseEntity<MerchantAuthDataResponse> getAuthData(@RequestParam(value="emailId") String emailId) {
        return authorizationDataServiceManager.getAuthData(emailId);
    }
	
	
}


