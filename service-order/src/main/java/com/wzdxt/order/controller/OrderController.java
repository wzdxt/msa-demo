package com.wzdxt.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wzdxt on 2018/6/23.
 */
@Controller
@RequestMapping
public class OrderController {

    @GetMapping("/list")
    @ResponseBody
    public List<String> getList() {
        return Arrays.asList("order_1", "order_2");
    }


}
