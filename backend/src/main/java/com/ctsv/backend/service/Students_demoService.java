package com.ctsv.backend.service;

import com.ctsv.backend.entity.Students_demo;
import com.ctsv.backend.repository.StudentdemoRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class Students_demoService {
    private final StudentdemoRepo repository;
    private final FileUploadService fileUploadService;

    public Students_demoService(StudentdemoRepo repo, FileUploadService fileUploadService) {
        this.repository = repo;
        this.fileUploadService = fileUploadService;
    }

    public List<Students_demo> getAllStudents() {
        return repository.findAll();
    }

    public Students_demo getStudentsById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
    }

    public Students_demo saveStudents(Students_demo students) {
        return repository.save(students);
    }

    public Students_demo updateStudent(Long id, Students_demo studentDetails) {
        Students_demo existingStudent = getStudentsById(id);
        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setPhone(studentDetails.getPhone());

        return repository.save(existingStudent);
    }

    @Transactional
    public Students_demo uploadAvatar(Long id, MultipartFile file) throws IOException {
        Students_demo student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
        String targetFileName = "avatar_" + id;
        String savedFileName = fileUploadService.storeFile(file, targetFileName);
        student.setAvatar(savedFileName);
        return repository.save(student);
    }
    @Transactional
    public void deleteStudents (Long id){
        Students_demo student=getStudentsById(id);
        if(student.getAvatar()!=null){
            fileUploadService.deleteFile(student.getAvatar());
        }
        repository.delete(student);
    }
}

