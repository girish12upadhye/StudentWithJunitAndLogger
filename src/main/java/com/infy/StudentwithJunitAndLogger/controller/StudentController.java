package com.infy.StudentwithJunitAndLogger.controller;


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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infy.StudentwithJunitAndLogger.baseEntity.BaseResponse;
import com.infy.StudentwithJunitAndLogger.entity.Student;
import com.infy.StudentwithJunitAndLogger.serviceI.StudentServiceI;

@RestController
public class StudentController
{
	@Autowired
	private StudentServiceI studentServiceI;
	
	@PostMapping(value="/savestudent" ,consumes = "application/json")
	public ResponseEntity<BaseResponse<Student>> saveStudent(@RequestBody Student stu)
	{
		Student student = studentServiceI.saveStudent(stu);
		BaseResponse<Student> base = new BaseResponse<Student>(201, "Student Saved Successfully", student);
		return ResponseEntity.status(HttpStatus.CREATED).body(base);
	}
	
	@GetMapping("/getallstudent")
	public ResponseEntity<BaseResponse<List<Student>>> getAllSudent()
	{
//		List<Student> slist = studentServiceI.getAllStudent();
//		BaseResponse<List<Student>> base = new BaseResponse<List<Student>>(200, "All Student Data", slist);
		return new ResponseEntity<BaseResponse<List<Student>>>(new BaseResponse<List<Student>>(200, "All Student Data", studentServiceI.getAllStudent()),HttpStatus.OK);
	}
	
	@GetMapping("/getstudentbyid/{sid}")
	public ResponseEntity<BaseResponse<Optional<Student>>> getStudentById(@PathVariable int sid)
	{
		Optional<Student> student = studentServiceI.getStudentById(sid);
		
		if(student.isPresent())
		{
		return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<Optional<Student>>(200, "This is Data Of Given Student Id", student));
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<Optional<Student>>(404, "Record With Given Id Not Found", null));
	}
	
	@PutMapping("updatestudentbyid/{sid}")
	public ResponseEntity<BaseResponse<Student>> updateStudentById(@RequestBody Student stu , @PathVariable int sid)
	{
		Optional<Student> studentById = studentServiceI.getStudentById(sid);
		if(studentById.isPresent())
		{
			return new ResponseEntity<BaseResponse<Student>>(new BaseResponse<Student>(404, "Record With Given Id Not Found", null), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<Student>(201, "Student Data Updated Successfully", studentServiceI.saveStudent(stu)));
	}
	
	@DeleteMapping("deletestudent/{sid}")
	public ResponseEntity<BaseResponse<Optional<Student>>> deleteStudent(@PathVariable int sid)
	{
		Optional<Student> studentById = studentServiceI.getStudentById(sid);
		if(studentById.isPresent())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<Optional<Student>>(404, "Record With Given Id Not Found", null));
		}
		studentServiceI.deleteStudent(sid);
		return new ResponseEntity<BaseResponse<Optional<Student>>>(new BaseResponse<Optional<Student>>(204, "Student Deleted Successfully", null), HttpStatus.NO_CONTENT);
	}
	// task-102 
	public String thanks(@PathVariable String name) {
		return name+", Thank You! Visit again";
	}
}
