/**
 * 
 */
package com.example.authdata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.stdimpl.GCacheFactory;
/**
 * @author rajeshsaw
 *
 */
@Service
public class AuthorizationDataServiceManager {
	
	private Cache getCacheInstance(){
	        Cache cache = null;
	        try {
	            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	            Map<Object, Object> properties = new HashMap<>();
	            properties.put(GCacheFactory.EXPIRATION_DELTA, TimeUnit.MINUTES.toSeconds(10));
	            cache = cacheFactory.createCache(properties);
	        } catch (CacheException e) {
	            e.printStackTrace();
	        }
	        return cache;
	}
	
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
        String key;      // ...
        byte[] value;    // ...
        Cache cache = getCacheInstance();
        // Put the value into the cache.
        cache.put(emailId, merchantAuthDataResponse.toString());
        cache.put(emailId+"1", "testCache");
        MemcacheServiceFactory.getAsyncMemcacheService("test").put(emailId, merchantAuthDataResponse.toString(), 
        		Expiration.byDeltaSeconds(300));
        MemcacheServiceFactory.getAsyncMemcacheService("test").put(emailId+"2", "tetAsysnc", 
        		Expiration.byDeltaSeconds(300));
        // Get the value from the cache.
        //value = (byte[]) cache.get(key);
		//System.out.println(value);
		return new ResponseEntity<>(merchantAuthDataResponse, HttpStatus.OK);
	}
	private boolean validate(String emailAddress) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailAddress);
		System.out.println(testResponse()); 
        return matcher.find();
	}
	
	public MerchantAuthDataResponse testResponse() {
		MerchantAuthDataResponse merchantAuthDataResponse = new MerchantAuthDataResponse();
		setEmail(merchantAuthDataResponse);
		setState(merchantAuthDataResponse);
		return merchantAuthDataResponse;
	}
	private void setEmail(MerchantAuthDataResponse merchantAuthDataResponse) {
		merchantAuthDataResponse.setEmailId("private void methods can modify oect");	
	}
	
	private void setState(MerchantAuthDataResponse merchantAuthDataResponse) {
		MerchantAuthDataAccess merchantAuthDataAccess = new MerchantAuthDataAccess();
		merchantAuthDataAccess.setAdmin(true);
		merchantAuthDataAccess.setManageAccount(true);
		merchantAuthDataResponse.setMerchantAuthDataAccess(merchantAuthDataAccess );	
	}
}
