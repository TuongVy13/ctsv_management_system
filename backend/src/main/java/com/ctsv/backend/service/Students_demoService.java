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
    public Students_demo updateStudent(Long id,Students_demo studentDetails){
        Students_demo existingStudent=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Không tìm thấy sinh viên id: "+id));
        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setPhone(studentDetails.getPhone());

        return repository.save(existingStudent);
    }
}
