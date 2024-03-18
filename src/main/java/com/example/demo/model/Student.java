package com.example.demo.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name="students")
public class Student {
	@Id
	@Column(nullable=false,unique=true,length=100)
	private String studentId;
	@Column(nullable=false,length=40)
    private String studentName;
	@Column(nullable=false,length=10)
    private String semister;
	@Column(nullable=false)
	private Date dob;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	private List<AttendanceAndDate> atd=new ArrayList<AttendanceAndDate>();
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSemister() {
		return semister;
	}
	public void setSemister(String semister) {
		this.semister = semister;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public List<AttendanceAndDate> getAtd() {
		return atd;
	}
	public void setAtd(List<AttendanceAndDate> atd) {
		this.atd = atd;
	}
	public Student(String studentId, String studentName, String semister, Date dob, List<AttendanceAndDate> atd) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.semister = semister;
		this.dob = dob;
		this.atd = atd;
	}
	public Student() {
		super();
	}

		    
}
