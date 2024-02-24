package com.infy.StudentwithJunitAndLogger.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.StudentwithJunitAndLogger.entity.Student;
import com.infy.StudentwithJunitAndLogger.serviceI.StudentServiceI;
@WebMvcTest(value = StudentController.class)
class StudentControllerTest
{
	@MockBean 
	private StudentServiceI studentServiceI;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("savestudent")
	void save() throws JsonProcessingException, Exception 
	{
		Student s1 = new Student(111, "Akshay", "Wai");
//		s1.setSid(111);
//		s1.setSname("Akshay");
//		s1.setSaddr("Wai");
		when(studentServiceI.saveStudent(s1)).thenReturn(s1);
//		this.mockMvc.perform(post("/savestudent")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(s1)))
//				.andExpect(status().isCreated());

		
		mockMvc.perform(post("/savestudent")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(s1)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.responseData.sname", is(s1.getSname())))
				.andExpect(jsonPath("$.responseData.saddr", is(s1.getSaddr())))
				.andExpect(jsonPath("$.responseData.sid", is(s1.getSid())));
		
	}
	@Test
	@DisplayName("getallstudent")
	void getAllSudent() throws Exception
	{
		Student s1 = new Student(111, "Akshay", "Wai");
		Student s2 = new Student(222, "Sagar", "Telangwadi");
		
		List<Student> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		
		when(studentServiceI.getAllStudent()).thenReturn(list);
		this.mockMvc.perform(get("/getallstudent"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.responseData.size()", is(list.size())));
	}
	
	@Test
	@DisplayName("getstudentbyid")
	void getStudentById() throws Exception
	{
		
		Student s2 = new Student(222, "Sagar", "Telangwadi");
		
		when(studentServiceI.getStudentById(anyInt())).thenReturn(Optional.of(s2));
		
		this.mockMvc.perform(get("/getstudentbyid/{sid}", 111))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.responseData.sid", is(s2.getSid())))
		.andExpect(jsonPath("$.responseData.sname", is(s2.getSname())))
		.andExpect(jsonPath("$.responseData.saddr", is(s2.getSaddr())));
	}
	@Test
	@DisplayName("deletestudent")
	void deleteStudent() throws Exception
	{
		Student s2 = new Student(222, "Sagar", "Telangwadi");
		
		doNothing().when(studentServiceI).deleteStudent(anyInt());
		
		this.mockMvc.perform(delete("/deletestudent/{sid}", 222))
		.andExpect(status().isNoContent());
	}
//	@Test
//	@DisplayName("updatestudentbyid/{sid}")
//	void updateStudentById()
//	{
//		Student s2 = new Student(222, "Sagar", "Telangwadi");
//		
//		when(studentServiceI.upda)
//	}
}
