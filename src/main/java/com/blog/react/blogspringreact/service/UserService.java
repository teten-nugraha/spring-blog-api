package com.blog.react.blogspringreact.service;

import com.blog.react.blogspringreact.entity.User;
import com.blog.react.blogspringreact.exception.UsernameAlreadyExistsException;
import com.blog.react.blogspringreact.repository.UserRepository;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User saveUser(User newUser) {
        try{

            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());

            return userRepository.save(newUser);

        }catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }
    }

}
