package com.ctsv.backend.controller;

import com.ctsv.backend.entity.Students_demo;
import com.ctsv.backend.service.FileUploadService;
import com.ctsv.backend.service.Students_demoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/students_demo")
@CrossOrigin(origins = "*")
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
    @PutMapping("/{id}")
    public Students_demo update(@PathVariable Long id,@RequestBody Students_demo studentDetails){
        return service.updateStudent(id,studentDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> delete(@PathVariable Long id){
        try{
            service.deleteStudents(id);
            return ResponseEntity.ok("Đã xóa thành công");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Lỗi không xóa được" + e.getMessage());
        }
    }
    @PostMapping("/{id}/upload-avatar")
    public ResponseEntity<?> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Students_demo updatedStudent = service.uploadAvatar(id,file);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            //return ResponseEntity.badRequest().body("Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Lỗi chi tiết: " + e.getMessage());
        }
    }

}
