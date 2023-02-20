package com.vandu.quiz.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.quiz.model.Student;
import com.vandu.quiz.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public <S extends Student> S save(S entity) {
		return studentRepository.save(entity);
	}

	public Student findByUsernameAndPassword(String username, String password) {
		return studentRepository.findByUsernameAndPassword(username, password);
	}

	public Student findByUsernameAndEmail(String username, String email) {
		return studentRepository.findByUsernameAndEmail(username, email);
	}

	public List<Student> findByFullnameContaining(String fullname) {
		return studentRepository.findByFullnameContaining(fullname);
	}

	public <S extends Student> List<S> saveAll(Iterable<S> entities) {
		return studentRepository.saveAll(entities);
	}

	public <S extends Student> Optional<S> findOne(Example<S> example) {
		return studentRepository.findOne(example);
	}

	public List<Student> findAll(Sort sort) {
		return studentRepository.findAll(sort);
	}

	public void flush() {
		studentRepository.flush();
	}

	public Page<Student> findAll(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	public <S extends Student> S saveAndFlush(S entity) {
		return studentRepository.saveAndFlush(entity);
	}

	public <S extends Student> List<S> saveAllAndFlush(Iterable<S> entities) {
		return studentRepository.saveAllAndFlush(entities);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public List<Student> findAllById(Iterable<String> ids) {
		return studentRepository.findAllById(ids);
	}

	public void deleteInBatch(Iterable<Student> entities) {
		studentRepository.deleteInBatch(entities);
	}

	public <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable) {
		return studentRepository.findAll(example, pageable);
	}

	public Optional<Student> findById(String id) {
		return studentRepository.findById(id);
	}

	public void deleteAllInBatch(Iterable<Student> entities) {
		studentRepository.deleteAllInBatch(entities);
	}

	public boolean existsById(String id) {
		return studentRepository.existsById(id);
	}

	public void deleteAllByIdInBatch(Iterable<String> ids) {
		studentRepository.deleteAllByIdInBatch(ids);
	}

	public <S extends Student> long count(Example<S> example) {
		return studentRepository.count(example);
	}

	public <S extends Student> boolean exists(Example<S> example) {
		return studentRepository.exists(example);
	}

	public void deleteAllInBatch() {
		studentRepository.deleteAllInBatch();
	}

	public Student getOne(String id) {
		return studentRepository.getOne(id);
	}

	public <S extends Student, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return studentRepository.findBy(example, queryFunction);
	}

	public long count() {
		return studentRepository.count();
	}

	public void deleteById(String id) {
		studentRepository.deleteById(id);
	}

	public Student getById(String id) {
		return studentRepository.getById(id);
	}

	public void delete(Student entity) {
		studentRepository.delete(entity);
	}

	public Student getReferenceById(String id) {
		return studentRepository.getReferenceById(id);
	}

	public void deleteAllById(Iterable<? extends String> ids) {
		studentRepository.deleteAllById(ids);
	}

	public void deleteAll(Iterable<? extends Student> entities) {
		studentRepository.deleteAll(entities);
	}

	public <S extends Student> List<S> findAll(Example<S> example) {
		return studentRepository.findAll(example);
	}

	public <S extends Student> List<S> findAll(Example<S> example, Sort sort) {
		return studentRepository.findAll(example, sort);
	}

	public void deleteAll() {
		studentRepository.deleteAll();
	}
	
	
}
