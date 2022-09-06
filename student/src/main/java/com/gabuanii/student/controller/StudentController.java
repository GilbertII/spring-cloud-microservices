package com.gabuanii.student.controller;

import com.gabuanii.student.request.CreateStudentRequest;
import com.gabuanii.student.response.StudentResponse;
import com.gabuanii.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@RefreshScope
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@Value("${prop.type}")
	private String type;
	
	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest studentRequest) {
		return studentService.createStudent(studentRequest);
	}
	
	@GetMapping("getById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		return studentService.getById(id);
	}

	@GetMapping("/test")
	public String test () {
		return type;
	}
	
}
