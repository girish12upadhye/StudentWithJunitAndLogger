package com.infy.StudentwithJunitAndLogger.repositoryI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.infy.StudentwithJunitAndLogger.entity.Student;

@DataJpaTest
class StudentRepositoryTest {
	@Autowired
	private StudentRepository studentRepository;

	Student s1;
	Student s2;

	@BeforeEach // Life Cycle Annotation
	void init() {
		s1 = new Student(111, "Akshay", "Wai");
		s2 = new Student(222, "Sagar", "Telangwadi");
	}

	@Test
	@DisplayName("StudentSave")
	void save() {

		
		// Act
		Student student = studentRepository.save(s1);

		// Assert
		assertNotNull(student);
		assertThat(student.getSid()).isNotEqualTo(null);
	}

	@Test
	@DisplayName("GetAllStudent")
	void getAllStudent() {

		studentRepository.save(s1);
		studentRepository.save(s2);

		List<Student> list = studentRepository.findAll();
		assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(2, list.size());
	}

	@Test
	@DisplayName("GetStudentById")
	void getStudentById() {

		studentRepository.save(s1);

		Student student = studentRepository.findById(s1.getSid()).get();
		assertNotNull(student);
		assertThat(student.getSid()).isEqualTo(s1.getSid());
		assertEquals("Akshay", student.getSname());
		assertEquals("Wai", student.getSaddr());
	}

	@Test
	@DisplayName("UpdateStudent")
	void updateStudent() {

		studentRepository.save(s1);

		Student student = studentRepository.findById(s1.getSid()).get();
		student.setSname("Akshya");
		student.setSaddr("Karanja");
		Student newStudent = studentRepository.save(student);

		assertEquals("Akshya", newStudent.getSname());
		assertEquals("Karanja", newStudent.getSaddr());
	}

	@Test
	@DisplayName("DeleteStudent")
	void deleteStudent() {
		studentRepository.save(s1);
		int id = s1.getSid();
		studentRepository.save(s2);

		studentRepository.delete(s1);
		Optional<Student> student = studentRepository.findById(id);

		List<Student> list = studentRepository.findAll();

		assertEquals(1, list.size());
		assertThat(student).isEmpty();
	}

	@Test
	@DisplayName("GetStudentBySaddr")
	void getStudentBySaddr() {
		studentRepository.save(s1);
		studentRepository.save(s2);

		List<Student> list = studentRepository.findBySaddr("Wai");

		assertNotNull(list);
		assertThat(list.size()).isEqualTo(1);
	}
}
