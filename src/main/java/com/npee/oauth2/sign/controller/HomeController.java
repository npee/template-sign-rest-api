package com.npee.oauth2.sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    @ResponseBody
    public Principal home(Principal principal) {
        return principal;
    }

    @GetMapping("/social/login")
    public String home() {
        return "social/login";
    }
}
