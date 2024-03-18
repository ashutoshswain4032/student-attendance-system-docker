package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.AttendanceAndDateRepository;
import com.example.demo.dao.StudentRepo;
import com.example.demo.dto.DateAndRollDTO;
import com.example.demo.model.AttendanceAndDate;

import com.example.demo.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceAndDateRepository adRepo;
	@Autowired
	private StudentRepo sRepo;

	@GetMapping("/attendance/new")
	public String addAttendance(Model model) {
		List<Student> std = sRepo.findAll();

		model.addAttribute("attd", new AttendanceAndDate());
		model.addAttribute("std", std);
		model.addAttribute("dto",new DateAndRollDTO());

		return "attendance_new";
	}

	@PostMapping("/save_attendance")
	public String saveAttendance(AttendanceAndDate ad, HttpServletRequest request,Model model) {
		String sdate = request.getParameter("sdate");
		Date date = Date.valueOf(sdate);
		List<Student> std = sRepo.findAll();
		model.addAttribute("attd", new AttendanceAndDate());
		model.addAttribute("std", std);
		model.addAttribute("dto",new DateAndRollDTO());
		List<AttendanceAndDate> atd = adRepo.findAll();
		for (AttendanceAndDate ad1 : atd) {
			if (ad1.getDate().equals(date) && ad1.getStudent().equals(ad.getStudent())) {
				return "attendance_new_error";
			}

		}

		ad.setDate(date);
		String s = request.getParameter("radio1");
		int random_int = (int) Math.floor(Math.random() * (6 - 1 + 1) + 1);
		if (s.equals("Present")) {
			ad.setCounter(random_int);
		} else {
			ad.setCounter(0);
		}

		adRepo.save(ad);

		return "attendance_new_success";

	}

}
