package com.wzdxt.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wzdxt on 2018/6/23.
 */
@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestTemplate directRestTemplate;

    @GetMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> index() {
        List<String> body = Arrays.asList("menu-1", "menu-2");
        return body;
    }


}
