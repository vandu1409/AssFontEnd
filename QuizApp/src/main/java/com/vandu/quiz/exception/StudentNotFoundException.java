package com.vandu.quiz.exception;

public class StudentNotFoundException  extends RuntimeException{

	public StudentNotFoundException() {
		super("Student not found");
	}
}
