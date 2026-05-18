package com.ctsv.backend.repository;

import com.ctsv.backend.entity.Students_demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentdemoRepo extends JpaRepository<Students_demo,Long> {
    //có sẵn findAll()
    //findById()
    //save()
    //deleteById()
}
