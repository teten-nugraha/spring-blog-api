package com.blog.react.blogspringreact.validator;

import com.blog.react.blogspringreact.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(user.getPassword().length() < 6) {
            errors.rejectValue("password","length","Password must be at least 6 char");
        }

        if(!user.getPassword().equalsIgnoreCase(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword","Match","Password Must Match");
        }
    }
}
