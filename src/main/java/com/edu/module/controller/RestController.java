package com.edu.module.controller;

import com.edu.module.controller.database.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RestController {


    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    public String getData(@RequestParam(value = "name", defaultValue = "anon") String name) {
        Data.data().insertNewUser(name);
        return "question";
    }
    @GetMapping("/")
    public String init() {
        return "index";
    }
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public String question(@RequestParam(value = "answer", defaultValue = "0") String answer, Model model){
        Integer val = (int) Math.round(Math.random() * 7);
        model.addAttribute("question",Data.data().select(String.valueOf(val)));
        System.out.println("Points:" + Data.data().getResult(Data.data().select(String.valueOf(val)),answer));
        System.out.println(val);

        return "question";
    }
}
