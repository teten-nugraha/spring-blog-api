package com.blog.react.blogspringreact.controller;

import com.blog.react.blogspringreact.entity.User;
import com.blog.react.blogspringreact.response.SuccessResponse;
import com.blog.react.blogspringreact.service.MapValidationErrorService;
import com.blog.react.blogspringreact.service.UserService;
import com.blog.react.blogspringreact.util.Status;
import com.blog.react.blogspringreact.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User newUser, BindingResult result) {

        userValidator.validate(newUser, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        User user = userService.saveUser(newUser);

        return SuccessResponse.response(
          user,
                Status.GET_ONE,
                null
        );

    }

}
