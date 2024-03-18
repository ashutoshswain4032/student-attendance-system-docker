package com.example.demo.model;

import java.sql.Date;
import javax.persistence.*;


@Entity
@Table(name="teachers")
public class Teacher {

	@Id
	@Column(nullable=false,unique=true,length=100)
	private String teacherId;
	@Column(nullable=false)
    private String teacherName;
	@Column(nullable=false,unique=true)
    private String email;
	@Column(nullable=false,length=40)
    private String password;
	@Column(nullable=false)
	private Date dob;
	@Column(nullable=false)
	private long number;
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	
	
	
}
