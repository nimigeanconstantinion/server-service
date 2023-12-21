package com.example.nserver.intercom.Security;


import com.example.nserver.dto.UserDTO;
import com.example.nserver.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "security-service",url = "http://localhost:8083/api/v1/security")
public interface SecurityClient {
//    @PostMapping("/login")
//    ResponseEntity<String> login(@RequestBody UserDTO user);
//
//    @PostMapping("/register")
//    ResponseEntity<String> register(@RequestBody UserDTO user);

}
