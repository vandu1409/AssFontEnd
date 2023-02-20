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

import com.vandu.quiz.model.Subject;
import com.vandu.quiz.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	public <S extends Subject> S save(S entity) {
		return subjectRepository.save(entity);
	}

	public <S extends Subject> List<S> saveAll(Iterable<S> entities) {
		return subjectRepository.saveAll(entities);
	}

	public <S extends Subject> Optional<S> findOne(Example<S> example) {
		return subjectRepository.findOne(example);
	}

	public List<Subject> findAll(Sort sort) {
		return subjectRepository.findAll(sort);
	}

	public void flush() {
		subjectRepository.flush();
	}

	public Page<Subject> findAll(Pageable pageable) {
		return subjectRepository.findAll(pageable);
	}

	public <S extends Subject> S saveAndFlush(S entity) {
		return subjectRepository.saveAndFlush(entity);
	}

	public <S extends Subject> List<S> saveAllAndFlush(Iterable<S> entities) {
		return subjectRepository.saveAllAndFlush(entities);
	}

	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}

	public List<Subject> findAllById(Iterable<Long> ids) {
		return subjectRepository.findAllById(ids);
	}

	
	public List<Subject> findByNameContaining(String name) {
		return subjectRepository.findByNameContaining(name);
	}

	public void deleteInBatch(Iterable<Subject> entities) {
		subjectRepository.deleteInBatch(entities);
	}

	public <S extends Subject> Page<S> findAll(Example<S> example, Pageable pageable) {
		return subjectRepository.findAll(example, pageable);
	}

	public Optional<Subject> findById(Long id) {
		return subjectRepository.findById(id);
	}

	public void deleteAllInBatch(Iterable<Subject> entities) {
		subjectRepository.deleteAllInBatch(entities);
	}

	public boolean existsById(Long id) {
		return subjectRepository.existsById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		subjectRepository.deleteAllByIdInBatch(ids);
	}

	public <S extends Subject> long count(Example<S> example) {
		return subjectRepository.count(example);
	}

	public <S extends Subject> boolean exists(Example<S> example) {
		return subjectRepository.exists(example);
	}

	public void deleteAllInBatch() {
		subjectRepository.deleteAllInBatch();
	}

	public Subject getOne(Long id) {
		return subjectRepository.getOne(id);
	}

	public <S extends Subject, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return subjectRepository.findBy(example, queryFunction);
	}

	public long count() {
		return subjectRepository.count();
	}

	public void deleteById(Long id) {
		subjectRepository.deleteById(id);
	}

	public Subject getById(Long id) {
		return subjectRepository.getById(id);
	}

	public void delete(Subject entity) {
		subjectRepository.delete(entity);
	}

	public Subject getReferenceById(Long id) {
		return subjectRepository.getReferenceById(id);
	}

	public void deleteAllById(Iterable<? extends Long> ids) {
		subjectRepository.deleteAllById(ids);
	}

	public void deleteAll(Iterable<? extends Subject> entities) {
		subjectRepository.deleteAll(entities);
	}

	public <S extends Subject> List<S> findAll(Example<S> example) {
		return subjectRepository.findAll(example);
	}

	public <S extends Subject> List<S> findAll(Example<S> example, Sort sort) {
		return subjectRepository.findAll(example, sort);
	}

	public void deleteAll() {
		subjectRepository.deleteAll();
	}
	
	
}
