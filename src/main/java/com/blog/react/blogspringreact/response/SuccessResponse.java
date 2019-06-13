package com.blog.react.blogspringreact.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class SuccessResponse {

    public static ResponseEntity<?> created(Object value, String status , String message) {

        if (status.equalsIgnoreCase("CREATED")) {

            Map<String, Object> response = new HashMap<>();
            response.put("data", value);
            response.put("message", message);
            response.put("success", true);


            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }

        return null;

    }

}
