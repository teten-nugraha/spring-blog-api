package com.blog.react.blogspringreact.response;

import com.blog.react.blogspringreact.util.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessResponse {

    private static final String[] statuses = new String[]{Status.CREATED, Status.GET_ALL, Status.DELETE, Status.GET_ONE};
    private static List<String> listStatus = Arrays.asList(statuses);

    public static ResponseEntity<?> response(Object value, String status , String message) {


        if(listStatus.contains(status)) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", value);
            response.put("message", message);
            response.put("success", true);


            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }



        return null;

    }



}
