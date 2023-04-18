package com.bracu.catfood.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BaseController {
    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}