package com.example.demo.dao;



import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.DateAndRollDTO;
import com.example.demo.model.Student;


public interface StudentRepo extends JpaRepository<Student, String> {
	List<Student> findByStudentId(String roll);
	@Query(value="SELECT new com.example.demo.dto.DateAndRollDTO(s.studentId,s.studentName,s.semister,a.date,a.counter) FROM Student s INNER JOIN s.atd a WHERE s.studentId=?1 AND a.date=?2")
	public List<DateAndRollDTO> fetchByRollAndDate(String studentId,Date date);
	
   }
