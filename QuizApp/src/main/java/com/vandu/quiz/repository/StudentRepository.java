package com.vandu.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandu.quiz.model.Student;

public interface StudentRepository extends JpaRepository<Student,String>  {

	List<Student> findByFullnameContaining(String fullname);
	Student findByUsernameAndPassword(String username,String password);
	Student findByUsernameAndEmail(String username,String email);
}
