package com.example.demo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, String> {
 List<Teacher> findByEmailAndPassword(String email,String password);

List<Teacher> findByemailAndDob(String email, Date doj);
}
