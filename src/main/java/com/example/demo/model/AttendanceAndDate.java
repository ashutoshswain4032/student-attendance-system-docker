package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.*;


@Entity
@Table(name = "attendance")
public class AttendanceAndDate {
	@Id
	@Column(nullable = false, unique = true, length = 100)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	private int counter;

	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student student;

	public AttendanceAndDate() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public AttendanceAndDate(int id, Date date, int counter, Student student) {
		super();
		this.id = id;
		this.date = date;
		this.counter = counter;
		this.student = student;
	}

}
