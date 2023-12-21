package com.example.nserver.intercom.Security;


import com.example.nserver.dto.UserDTO;
import com.example.nserver.model.MapStocOpt;
import com.example.nserver.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SecurityAdapter {
//    private SecurityClient securityClient;
//
//    public SecurityAdapter(SecurityClient securityClient){
//        this.securityClient=securityClient;
//    }
//
//    public String login(UserDTO user){
//        ResponseEntity<String> response=securityClient.login(user);
//
//        if(response.getStatusCode()== HttpStatus.OK){
//            return response.getBody();
//        }
//        throw new RuntimeException("Authentication error!!!");
//
//    }
//
//    public String register(UserDTO user){
//        ResponseEntity<String> response=securityClient.register(user);
//
//        if(response.getStatusCode()== HttpStatus.OK){
//            return response.getBody();
//        }
//        throw new RuntimeException("Can't register!!!");
//
//    }

}
