package com.example.demo.dto;

import java.util.*;

public class DateAndRollDTO {
    private String studentId;
    private String studentName;
    private String semister;
    private Date date;
    private int counter;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public DateAndRollDTO(String studentId, String studentName, String semister, Date date, int counter) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.semister = semister;
		this.date = date;
		this.counter = counter;
	}
	public DateAndRollDTO() {
		super();
	}
	@Override
	public String toString() {
		return "DateAndRollDTO [studentId=" + studentId + ", studentName=" + studentName + ", semister=" + semister
				+ ", date=" + date + ", counter=" + counter + "]";
	}
    
    
}
