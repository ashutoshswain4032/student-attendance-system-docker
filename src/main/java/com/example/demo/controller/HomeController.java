package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AdminRepo;
import com.example.demo.dao.StudentRepo;
import com.example.demo.dao.TeacherRepo;
import com.example.demo.dto.DateAndRollDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.AttendanceAndDate;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

@Controller
public class HomeController {

	@Autowired
	AdminRepo repo;
	@Autowired
	StudentRepo srepo;
	@Autowired
	TeacherRepo trepo;

// admin login
	@GetMapping("/admin/login")
	public String showStudent(Model model) {
		model.addAttribute("admin", new Admin());
		model.addAttribute("student", new Student());
		return "adminlogin";
	}
	@GetMapping("/admin/login2")
	public String showStudentNew(Model model) {
		model.addAttribute("admin", new Admin());
		return "adminlogin2";
	}
 //to check admin login
	@GetMapping("/admin/login/show")
	public String showFindPage(Model model, @RequestParam String userName, @RequestParam String password) {
		List<Admin> admin = repo.findByuserNameAndPassword(userName, password);
		List<Student> std = srepo.findAll();
		List<Teacher> tc = trepo.findAll();
		model.addAttribute("std", std);
		model.addAttribute("tc", tc);

		for (Admin a : admin) {
			if (a.getUserName().equals(userName) && a.getPassword().equals(password)) {
				return "afteradminlogin";
			}
		}
		return "redirect:/admin/login2";
	}
	@GetMapping("/admin/login/show1")
	public String showFindPage1(Model model) {
		List<Student> std = srepo.findAll();
		List<Teacher> tc = trepo.findAll();
		model.addAttribute("tc", tc);
		model.addAttribute("std", std);
		return "afteradminlogin2";
	}
	@GetMapping("/admin/login/show2")
	public String showStudent2(Model model) {
		List<Student> std = srepo.findAll();
		model.addAttribute("std", std);
		return "afteradminlogin";
	}

// teacher registration
	@GetMapping("/admin/login/teacher/reg")
	public String goTeacherReg(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "teacherregistration2";
	}
	@GetMapping("/register/new/teacher")
	public String showRegisterPage1(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "teacherregistration";
	}
	@PostMapping("/addTeacher/register")
	public String showRegisterPage2(@ModelAttribute("teacher") Teacher teacher) {
		trepo.save(teacher);
		return "redirect:/register/new/teacher";
	}
	
// teacher login
	@GetMapping("/teacher/login")
	public String teacherLogin(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "teacherlogin";
	}
	@GetMapping("/teacher/login2")
	public String teacherLogin2(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "teacherlogin2";
	}
//to check teacher login
	@GetMapping("/teacher/login/view")
	public String teacherLogin2(Model model, @RequestParam String email, @RequestParam String password) {
		List<Teacher> teacher = trepo.findByEmailAndPassword(email, password);
		List<Student> std = srepo.findAll();
		for (Teacher a : teacher) {
			if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
				model.addAttribute("teacher", teacher);
				model.addAttribute("std", std);
				model.addAttribute("date", new AttendanceAndDate());
				model.addAttribute("dto", new DateAndRollDTO());
				return "teacher_profile";
			}
		}
		return "redirect:/teacher/login2";
	}
		
//view student list
	@GetMapping("/view/studentlist")
	public String viewStudentList(Model model) {
		List<Student> std = srepo.findAll();
		model.addAttribute("std", std);
		model.addAttribute("date", new AttendanceAndDate());
		model.addAttribute("dto", new DateAndRollDTO());
		return "view_student_list";
	}
	
//forgot password for admin
	@GetMapping("/forgot/password")
	public String forgotPassword(Model model) {

		model.addAttribute("admin", new Admin());
		return "forgot_password";

	}
	@GetMapping("/reset/password")
	public String resetPassword(Model model, @RequestParam int adminId, @RequestParam String userName) {
		List<Admin> admin = repo.findByadminIdAndUserName(adminId, userName);
		model.addAttribute("admin", new Admin());
		for (Admin a : admin) {
			if (a.getUserName().equals(userName) && a.getAdminId() == adminId) {
				Admin ad = repo.findById(a.getAdminId()).get();
				model.addAttribute("ad", ad);
				return "reset_password_before";
			}
		}
		return "reset_password_error";
	}
	@GetMapping("/admin/reset/{id}")
	public String editStudent(@PathVariable int id, Model model) {

		Admin admin = repo.findById(id).get();
		model.addAttribute("admin", admin);
		return "reset_admin";
	}

	@PostMapping("/admin/update/{id}")
	public String updateAdmin(@ModelAttribute("admin") Admin admin, Model model) {
		repo.save(admin);
		return "reset_password_success";
	}

//forgot password for teacher
	@GetMapping("/forgot/password/teacher")
	public String forgotPassword1(Model model) {
		model.addAttribute("tc", new Teacher());
		return "forgot_password_teacher";
	}
	@GetMapping("/reset/password/teacher")
	public String resetPassword1(Model model,@RequestParam String email, @RequestParam Date dob) {
		List<Teacher> tc = trepo.findByemailAndDob(email, dob);
		model.addAttribute("tc", new Teacher());
		for (Teacher a : tc) {
			if (a.getEmail().equals(email) && a.getDob().equals(dob)) {
				Teacher ad = trepo.findById(a.getTeacherId()).get();
				model.addAttribute("ad", ad);
				return "reset_password_before_teacher";
			}
		}
		return "reset_password_error_teacher";
	}
	@GetMapping("/teacher/reset/{id}")
	public String editStudent1(@PathVariable String id, Model model) {

		Teacher tc = trepo.findById(id).get();
		model.addAttribute("tc", tc);
		return "reset_teacher";
	}

	@PostMapping("/teacher/update/{id}")
	public String updateAdmin2(@ModelAttribute("tc") Teacher tc, Model model) {
		trepo.save(tc);
		return "reset_password_success_teacher";
	}
}
