/**
 * 
 */
package com.example.authdata;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rajeshsaw
 *
 */
@RestController
public class AuthorizationDataService {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;
	@Autowired
	AuthorizationDataServiceManager authorizationDataServiceManager;
	@RequestMapping("/authdata")
    public ResponseEntity<MerchantAuthDataResponse> getAuthData(@RequestParam(value="emailId") String emailId) {
        return authorizationDataServiceManager.getAuthData(emailId);
    }
	
	@GetMapping("/getTuples")
	public String getTuples() {
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return this.jdbcTemplate.queryForObject("select id from test", String.class);
				//.toString();
				
	}
	
	
}


