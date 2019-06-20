package com.blog.react.blogspringreact.controller;

import com.blog.react.blogspringreact.entity.Feedback;
import com.blog.react.blogspringreact.entity.User;
import com.blog.react.blogspringreact.payload.JWTLoginSuccessResponse;
import com.blog.react.blogspringreact.payload.LoginRequest;
import com.blog.react.blogspringreact.response.SuccessResponse;
import com.blog.react.blogspringreact.security.JwtTokenProvider;
import com.blog.react.blogspringreact.security.SecurityConstants;
import com.blog.react.blogspringreact.service.MapValidationErrorService;
import com.blog.react.blogspringreact.service.UserService;
import com.blog.react.blogspringreact.util.EmailCfg;
import com.blog.react.blogspringreact.util.Status;
import com.blog.react.blogspringreact.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailCfg emailCfg;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt  = SecurityConstants.TOKEN_PREFIX+ tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {

        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> feedback(@RequestBody Feedback feedback, BindingResult result) {

        if(result.hasErrors()){
            throw new ValidationException("Invalid");
        }

        try{
            // Create a mail sender
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(this.emailCfg.getHost());
            mailSender.setPort(this.emailCfg.getPort());
            mailSender.setUsername(this.emailCfg.getUsername());
            mailSender.setPassword(this.emailCfg.getPassword());
            // Create an email instance
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(feedback.getEmail());
            mailMessage.setTo("teten@hackflix.com");
            mailMessage.setSubject("New feedback from " + feedback.getName());
            mailMessage.setText(feedback.getFeedback());
            // Send mail
            mailSender.send(mailMessage);

            return SuccessResponse.response(
              feedback,
                    Status.SUCCESS,
                    "Email Anda sudah terkirim  "
            );

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
