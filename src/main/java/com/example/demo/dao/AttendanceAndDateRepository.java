package com.example.demo.dao;

import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AttendanceAndDate;


public interface AttendanceAndDateRepository extends JpaRepository<AttendanceAndDate, Integer> {
	List<AttendanceAndDate> findByDate(Date date);
	List<AttendanceAndDate> findByDateBetween(Date sdate,Date edate,Sort sort);
   }
