package com.vandu.quiz.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

	@Id
	@Column(columnDefinition = "nvarchar(200)")
	private String username;
	
	private String password;
	
	private String fullname;
	
	private String email;
	
	private boolean gender;
	
	private Date birthday;
	
	private Double schoolFee;
	
	private Double marks;
	
	
}
