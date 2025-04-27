package com.oxyl.epf.controller;
import org.springframework.web.bind.annotation.RequestMapping;
public class CoursEpfBackController{
    @RequestMapping("/")
    public String home() {
        //Cette méthode renvoie la vue "index"
        return "index";
    }
}
