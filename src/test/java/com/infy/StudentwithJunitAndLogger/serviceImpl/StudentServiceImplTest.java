package com.infy.StudentwithJunitAndLogger.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.StudentwithJunitAndLogger.entity.Student;
import com.infy.StudentwithJunitAndLogger.repositoryI.StudentRepository;
import com.infy.StudentwithJunitAndLogger.serviceI.StudentServiceI;
@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class StudentServiceImplTest 
{

	@InjectMocks
//	@Autowired
	private StudentServiceImpl studentServiceI;
	
	@Mock
	private StudentRepository studentRepository;
	
	Student s1 ;
	Student s2 ;
	
	@BeforeEach
	void init()
	{
//		studentServiceI = new StudentServiceImpl(studentRepository);
		s1 = new Student(111, "Akshay", "Wai");
		s2 = new Student(222, "Sagar", "Telangwadi");
	}
	
	@Test
	@DisplayName("SaveStudent")
	void save() 
	{
//		 Student s1 = new Student(111, "Akshay", "Wai");
//		 Student s2 = new Student(222, "Sagar", "Telangwadi");
		 
		 when(studentRepository.save(any(Student.class))).thenReturn(s1);
		 Student student = studentServiceI.saveStudent(s1);
		 assertNotNull(student);
		 assertThat(student.getSname()).isEqualTo("Akshay");
		 assertEquals(student.getSaddr(), "Wai");
	}
	
	@Test
	@DisplayName("GetAllStudent")
	void getAllStudent()
	{
//		Student s1 = new Student(111, "Akshay", "Wai");
//		Student s2 = new Student(222, "Sagar", "Telangwadi");
		
		List<Student> list = new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		
		when(studentRepository.findAll()).thenReturn(list);
		List<Student> list2 = studentServiceI.getAllStudent();
		
		assertEquals(2, list2.size());
//		assertNotNull(list2);
		assertThat(list2).isNotNull();
	}
	
	@Test
	@DisplayName("getstudentbyid")
	void getStudentById()
	{
//		Student s1 = new Student(111, "Akshay", "Wai");
		
		when(studentRepository.findById(anyInt())).thenReturn(Optional.of(s1));
		
		Student student = studentServiceI.getStudentById(s1.getSid()).get();
		assertNotNull(student);
		assertEquals(student.getSid(),111);
		assertThat(student.getSname()).isEqualTo(s1.getSname());
		assertThat(student.getSaddr()).isEqualTo(s1.getSaddr());
	}
	
	@Test
	@DisplayName("getstudentbyidthrowexception")
	void getStudentByIdThrowException()
	{
//		Student s1 = new Student(111, "Akshay", "Wai");
		
		when(studentRepository.findById(111)).thenReturn(Optional.of(s1));
		assertThrows(RuntimeException.class, ()->{
			studentServiceI.getStudentById(222);
		});
	}
	
	@Test
	@DisplayName("updateStudent")
	void updateStudent()
	{
//		Student s1 = new Student(111, "Akshay", "Wai");
		
		when(studentRepository.findById(anyInt())).thenReturn(Optional.of(s1));
		when(studentRepository.save(any(Student.class))).thenReturn(s1);
		s1.setSname("Akshya");
		s1.setSaddr("Karanja");
		
		Student student = studentServiceI.updateStudentById(s1, 111);
		
		assertNotNull(student);
		assertEquals("Akshya", student.getSname());
		assertEquals("Karanja", student.getSaddr());
	}
	
	@Test
	@DisplayName("deletestudent")
	void deleteStudent()
	{
//		Student s1 = new Student(111, "Akshay", "Wai");
		
		when(studentRepository.findById(111)).thenReturn(Optional.of(s1));
		doNothing().when(studentRepository).deleteById(111);
		
		studentServiceI.deleteStudent(111);
		verify(studentRepository ,times(1)).deleteById(s1.getSid());
	}
	

}
