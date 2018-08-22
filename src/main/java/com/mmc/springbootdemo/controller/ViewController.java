package com.mmc.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web")
public class ViewController {

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showHtml(Model model){
        model.addAttribute("hello", "html内容");
        return "show";
    }
}