package com.bracu.catfood.service;

import lombok.Data;

@Data
public class AuthUser {
    public String username;
    public Integer userType;
    public Integer userId;
}