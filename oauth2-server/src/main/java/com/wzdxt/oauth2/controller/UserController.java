package com.wzdxt.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wzdxt on 2018/6/23.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ApplicationContext cxt;

    @GetMapping("/get")
    @ResponseBody
    public Object get(Principal principal) {
        if (principal == null) {
            throw new UnauthorizedUserException("not authenticated");
        }
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("username", principal.getName());
        return user;
    }


}
