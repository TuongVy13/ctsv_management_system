package com.ctsv.backend.service;

import com.ctsv.backend.entity.Students_demo;
import com.ctsv.backend.repository.StudentdemoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Students_demoService {
    private final StudentdemoRepo repository;
    public Students_demoService(StudentdemoRepo repo){
        this.repository=repo;
    }
    public List<Students_demo> getAllStudents(){
        return repository.findAll();
    }
    public Students_demo saveStudents(Students_demo students){
        return  repository.save(students);
    }
}
