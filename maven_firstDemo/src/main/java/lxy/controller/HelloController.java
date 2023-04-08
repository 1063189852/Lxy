package lxy.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {


    @RequestMapping(value = "/world",method= RequestMethod.GET)
    public String test(){
        return "hello world!";
    }

}
