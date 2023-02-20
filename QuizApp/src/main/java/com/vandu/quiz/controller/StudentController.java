package com.vandu.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.quiz.domain.FormChangePassword;
import com.vandu.quiz.exception.StudentNotFoundException;
import com.vandu.quiz.model.Student;
import com.vandu.quiz.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping()
	public List<Student> getAll() {
		return studentService.findAll();
	}

	@GetMapping("search/{username}")
	public Student findById(@PathVariable String username) {
		Student student = studentService.findById(username).orElseThrow(() -> new StudentNotFoundException());

		return student;
	}

	@GetMapping("searchByName/{fullname}")
	public List<Student> searchByName(@PathVariable String fullname) {
		return studentService.findByFullnameContaining(fullname);
	}

	@PostMapping()
	public Student insert(@RequestBody Student student) {
		return studentService.save(student);
	}

	@PutMapping("updateInfo/{username}")
	public Student update(@PathVariable String username, @RequestBody Student student) {
		Student stu = studentService.findById(username).get();

		if (stu != null) {
			if (student.getFullname() != null) {
				stu.setFullname(student.getFullname());
			}
			
			if ( student.getEmail() != null) {
				stu.setEmail(student.getEmail());
			}
			
			if (student.getBirthday() != null) {
				stu.setBirthday(student.getBirthday());
			}
			
		
			
			stu.setGender(student.isGender());

			return studentService.save(stu);
		}

		return null;

	}

	@PutMapping("changepass/{username}")
	public Student changePass(@PathVariable String username,@RequestBody FormChangePassword form) {
		Student student = studentService.findById(username).get();

		if (student != null) {
			if (student.getPassword().equalsIgnoreCase(form.getPassword())) {
				student.setPassword(form.getNewPassword());

				return studentService.save(student);
			}
		}

		return null;
	}
	
	@PostMapping("forgetPass")
	public Student fogetPassword(@RequestBody Student student) {
		Student stu = studentService.findByUsernameAndEmail	(student.getUsername(), student.getEmail());

		if(stu!=null) {
			return stu;
		}

		return null;
	}

	@PostMapping("checkLogin")
	public Student checkLogin(@RequestBody Student student) {
		return studentService.findByUsernameAndPassword(student.getUsername(), student.getPassword());
	}

}
