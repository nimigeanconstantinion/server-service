package com.example.nserver.service;

import com.example.nserver.dto.UserDTO;
import com.example.nserver.model.User;
import com.example.nserver.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    UserRepo userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    public User getUserFromEmail(String email){
        Optional<User> user=userRepo.findUserByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    public User addUser(User usr){
        Optional<User> user=userRepo.findUserByEmail(usr.getEmail());
        if(user.isEmpty()){
            userRepo.save(usr);
            return userRepo.findUserByEmail(usr.getEmail()).get();
        }
        return null;

    }

    public UserDTO userToDTO(User usr){

        UserDTO usrDTO=new UserDTO();
        usrDTO.setName(usr.getName());
        usrDTO.setEmail(usr.getEmail());
        usrDTO.setRole(usr.getRole().name());
        return usrDTO;
    }


}
