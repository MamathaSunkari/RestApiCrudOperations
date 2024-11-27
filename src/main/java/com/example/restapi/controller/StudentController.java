package com.example.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.entity.Student;
import com.example.restapi.repository.StudentRepository;

@RestController
//@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	//Get all the students
	//localhost:8080/students
	
	@GetMapping("/students")
	public List<Student> getAllStudents()
	{
		List<Student> students = studentRepository.findAll();
		return students;
		
	}
	
	@GetMapping("/students/{id}")
	public Optional<Student> getStudent( @PathVariable int id)
	{
		Optional<Student> stu = studentRepository.findById(id);
		
//		Student stud = studentRepository.findById(id).get();
		return stu;
		
	}
	
    @PostMapping("/student/add")
//	public void createStudent(@RequestBody Student student)
//	{
//	     studentRepository.save(student);
//		
//	}
    public ResponseEntity<Student> createStudent(@RequestBody Student student)
    {
    	return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }
    @PutMapping("/student/{id}")
    public void updateStudent( @PathVariable int id)
    {
	 Student stud = studentRepository.findById(id).get();
	 stud.setName("vani");
	 stud.setBranch("civil");
	 stud.setPercentage(50);
	 studentRepository.save(stud);
    	
    }
    
    @DeleteMapping("/student/delete/{id}")
    public void deleteStudent(@PathVariable int id)
    {
    Student s =	studentRepository.findById(id).get();
    studentRepository.delete(s);
    }
}
