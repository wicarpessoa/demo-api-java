package com.br.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/{name}")
    public String helloName(@PathVariable String name){
        return "Hi " + name + ", nice to meet you!";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "nome") String nome){
        return "Hello " + nome + ", seja bem-vindo!";
    }

}
