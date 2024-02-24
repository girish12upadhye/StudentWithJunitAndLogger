package com.infy.StudentwithJunitAndLogger.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.infy.StudentwithJunitAndLogger.baseEntity.BaseResponse;
import com.infy.StudentwithJunitAndLogger.entity.Student;
import com.infy.StudentwithJunitAndLogger.repositoryI.StudentRepository;
import com.infy.StudentwithJunitAndLogger.serviceI.StudentServiceI;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentServiceI
{

	@Autowired
	private StudentRepository studentRepository;
	
//	public StudentServiceImpl(StudentRepository studentRepository) 
//	{
//		super();
//		this.studentRepository = studentRepository;
//	}

	
	@Override
	public Student saveStudent(Student stu) 
	{
		return studentRepository.save(stu);
	}
	@Override
	public List<Student> getAllStudent() {
		
		return studentRepository.findAll();
	}
	@Override
	public Optional<Student> getStudentById(int sid) 
	{
		return Optional.of(studentRepository.findById(sid).orElseThrow(()-> new RuntimeException("Student Not Found For Given Id "+ sid)));
	}
	
	public Student updateStudentById(Student stu , int sid)
	{
		Optional<Student> studentById = studentRepository.findById(sid);
		if(studentById.isPresent())
		{
			return studentRepository.save(stu);
			
		}
		
		return null;
	}
	@Override
	public void deleteStudent(int sid) 
	{
		
		Optional<Student> studentById = studentRepository.findById(sid);
		if(studentById.isPresent())
		{
			studentRepository.deleteById(sid);
//			return null;
		}
		
			
//		return studentRepository.save(stu);
	}
	
}
