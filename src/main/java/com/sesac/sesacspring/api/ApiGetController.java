package com.sesac.sesacspring.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiGetController {
    @GetMapping("/api-get")
    public String  getMain(){
        return "apiGet";
    }


    @GetMapping({"/introduce2"})
    public String getResponse(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) String age,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "apiGetResponse";
    }

    @GetMapping("/introduce1")
    public String getResponse2(
        @RequestParam(value = "name", required = false) String name, Model model
    ){
        model.addAttribute("name2", name);
        return "apiGetResponse";
    }

    //    @GetMapping("/")
//    public String getMain() { return "apiGet"; }
//    @GetMapping("/get/apiGet/{name}/{age}")
//    public String getResponse(
//            @RequestParam(value = "name") String name,
//            @RequestParam(value = "age") String age,
//            Model model) {
//        model.addAttribute("name", name);
//        model.addAttribute("age", age);
//
//        return "apiGetResponse";
//    }

    @PostMapping("/post/form-test")
    @ResponseBody
    public String postResponse3(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "birth", required = false) String birth,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "hobby", required = false) String hobby,
            Model model){

        return "이름: " + name + ", 성별: " + gender + ", 생년월일: " + birth + ", 관심사: " + hobby ;
    }
}
