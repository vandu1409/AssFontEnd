package com.vandu.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandu.quiz.model.Subject;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Long>{

	List<Subject> findByNameContaining(String name);
}
