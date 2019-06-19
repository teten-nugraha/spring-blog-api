package com.blog.react.blogspringreact.payload;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JWTLoginSuccessResponse {

    private boolean success;
    private String token;

    public JWTLoginSuccessResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }
}
