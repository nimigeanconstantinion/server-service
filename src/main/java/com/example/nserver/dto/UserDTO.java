package com.example.nserver.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String role;
    private String token;

    public UserDTO(String email,String password){
        this.email=email;
        this.password=password;
    }

}
