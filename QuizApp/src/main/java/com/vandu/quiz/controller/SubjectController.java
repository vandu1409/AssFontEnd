package com.vandu.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.quiz.exception.SubjectNotFoundException;
import com.vandu.quiz.model.Subject;
import com.vandu.quiz.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping()
	public List<Subject> getAll() {

		return subjectService.findAll();
	}

	@GetMapping("{id}/get")
	public Subject get(@PathVariable Long id) {
		Subject subject = subjectService.findById(id).orElseThrow(() -> new SubjectNotFoundException());

		return subject;
	}

	@PutMapping("update/{id}")
	public Subject update(@PathVariable Long id, @RequestBody Subject subject) {
		return subjectService.findById(id).map(sub -> {
			sub.setName(subject.getName());
			sub.setLogo(subject.getLogo());

			return subjectService.save(sub);
		}).get();
	}

	@GetMapping("searchByName/{name}")
	public List<Subject> searchByName(@PathVariable String name) {
		return subjectService.findByNameContaining(name);
	}

	@PostMapping("")
	public Subject insert(@RequestBody Subject subject) {
		return subjectService.save(subject);
	}
}
