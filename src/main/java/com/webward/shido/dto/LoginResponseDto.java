package com.webward.shido.dto;

/**
 * Created by dustinosthzn on 2014/12/27.
 */
public class LoginResponseDto {
    public String token;
    public String message;
    public boolean success;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
