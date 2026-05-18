package com.ctsv.backend.controller;

import com.ctsv.backend.entity.Students_demo;
import com.ctsv.backend.service.Students_demoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students_demo")
public class Students_demoController {
    private  final Students_demoService service;
    public Students_demoController(Students_demoService service){
        this.service=service;
    }
    @GetMapping
    public List<Students_demo> getAllstudents(){
        return service.getAllStudents();
    }
    @PostMapping("/create")
    public Students_demo create(@RequestBody Students_demo student){
        return service.saveStudents(student);
    }
}
