package com.bracu.catfood.service;

import lombok.Data;

@Data
public class SignupUser {
    public String username;
    public String password;
    public String confirmPassword;
}
