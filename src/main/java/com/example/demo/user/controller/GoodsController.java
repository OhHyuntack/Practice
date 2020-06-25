package com.example.demo.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class GoodsController {


    @GetMapping("/goods/info")
    public String goodsInfo() {
        return "goods/info";
    }

    @GetMapping("/goods/show")
    public String goodsShow(){return "goods/show";}

}
