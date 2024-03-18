package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.dao.AdminRepo;
import com.example.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AttendanceAndDateRepository;
import com.example.demo.dao.StudentRepo;
import com.example.demo.dao.TeacherRepo;
import com.example.demo.dto.DateAndRollDTO;
import com.example.demo.model.AttendanceAndDate;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

	@Autowired
	AdminRepo adminRepo;
	@Autowired
	StudentRepo repo;

	@Autowired
	AttendanceAndDateRepository adRepo;

	@Autowired
	TeacherRepo trepo;
//home page of student attendance system
	@GetMapping("/")
	public String showLoginPage() {
		Admin admin =new Admin();
		admin.setUserName("ashutosh4032");
		admin.setPassword("ashutosh4032");
		Optional<Admin> byUserName = adminRepo.findByUserName(admin.getUserName());
		if(byUserName.isEmpty()){
			adminRepo.save(admin);
		}
		return "index";
	}

// Student Registration by Admin
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("student", new Student());
		return "studentregistration2";
	}

	@GetMapping("/register/new")
	public String showRegisterPage1(Model model) {
		model.addAttribute("student", new Student());
		return "studentregistration";
	}

	@PostMapping("/addStudent/register")
	public String showRegisterPage2(@ModelAttribute("student") Student student) {
		repo.save(student);
		return "redirect:/register/new";
	}

// for updating student information
	@GetMapping("/student/edit/{id}")
	public String editStudent(@PathVariable String id, Model model) {

		Student std = repo.findById(id).get();
		model.addAttribute("std", std);
		return "edit_student";
	}

	@PostMapping("/students/update/{id}")
	public String updateStudent(@ModelAttribute("std") Student std, Model model) {
		repo.save(std);
		return "redirect:/admin/login/show3";
	}

	@GetMapping("/admin/login/show3")
	public String showStudent2(Model model) {
		List<Student> std = repo.findAll();
		List<Teacher> tc = trepo.findAll();
		model.addAttribute("std", std);
		model.addAttribute("tc", tc);
		return "after_edit_student";
	}

// for deleting a student information
	@GetMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable String id) {
		repo.deleteById(id);
		return "redirect:/admin/login/show3";
	}

// for updating teacher information
	@GetMapping("/teacher/edit/{id}")
	public String editTeacher(@PathVariable String id, Model model) {

		Teacher tc = trepo.findById(id).get();
		model.addAttribute("tc", tc);
		return "edit_teacher";
	}

	@PostMapping("/teachers/update/{id}")
	public String updateTeacher(@ModelAttribute("tc") Teacher tc, Model model) {
		trepo.save(tc);
		return "redirect:/admin/login/show3";
	}

// for deleting a teacher information
	@GetMapping("/teacher/delete/{id}")
	public String deleteTeacher(@PathVariable String id) {
		trepo.deleteById(id);
		return "redirect:/admin/login/show3";
	}

// for view a student by Date
	@GetMapping("/view/attendanceByDate")
	String viewAttendanceByDate(@RequestParam Date date, Model model) {
		List<AttendanceAndDate> ad = adRepo.findByDate(date);
		model.addAttribute("ad", ad);
		List<Student> std = repo.findAll();
		model.addAttribute("std", std);
		model.addAttribute("date", new AttendanceAndDate());
		model.addAttribute("dto", new DateAndRollDTO());
		return "view_attendance";
	}

// for view students by roll and date
	@GetMapping("/view/attendanceByRoll")
	String viewAttendanceByRoll(@RequestParam String studentId, @RequestParam Date date, Model model) {
		List<DateAndRollDTO> ad = repo.fetchByRollAndDate(studentId, date);
		List<Student> std = repo.findAll();
		model.addAttribute("ad", ad);
		model.addAttribute("std", std);
		model.addAttribute("date", new AttendanceAndDate());
		model.addAttribute("dto", new DateAndRollDTO());
		return "view_attendance2";
	}

//for generating report
	@GetMapping("/view/attendanceBetweenDates")
	String viewAttendanceByDate(HttpServletRequest request,Model model) {
		String date1 = request.getParameter("sdate");
		Date sdate = Date.valueOf(date1);
		String date2 = request.getParameter("edate");
		Date edate = Date.valueOf(date2);
		List<AttendanceAndDate> ad = adRepo.findByDateBetween(sdate,edate,Sort.by("date").ascending());
		model.addAttribute("ad", ad);
		List<Student> std = repo.findAll();
		model.addAttribute("std", std);
		model.addAttribute("date", new AttendanceAndDate());
		model.addAttribute("dto", new DateAndRollDTO());
		return "report";
	}
}
