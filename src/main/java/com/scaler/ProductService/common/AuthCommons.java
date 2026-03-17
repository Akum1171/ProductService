package com.scaler.ProductService.common;

import com.scaler.ProductService.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {

    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    // userDto and Role we copied from UserService so that we can store the data
    public UserDto validateToken(String tokenValue){
        // call the user service to validate the token
        ResponseEntity<UserDto> responseEntity= restTemplate.getForEntity("http://localhost:8082/users/validate/" +tokenValue, UserDto.class);
        if(responseEntity.getBody()==null){
            //means token is invalid
            // thorw exception
            return null;
        }
        return responseEntity.getBody();
    }
}
