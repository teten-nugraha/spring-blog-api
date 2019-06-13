package com.blog.react.blogspringreact.response;

import com.blog.react.blogspringreact.util.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {

    public static ResponseEntity<?> error (Object value, String status , String message, Object errorCode) {

        if(status.equalsIgnoreCase(Status.ERROR)) {

            Map<String, Object> response = new HashMap<>();
            response.put("data", value);
            response.put("message", message);
            response.put("success", false);
            response.put("errorCode", (Integer)errorCode);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        return  null;

    }

}
