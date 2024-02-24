package com.infy.StudentwithJunitAndLogger.serviceI;

import java.util.List;
import java.util.Optional;

import com.infy.StudentwithJunitAndLogger.entity.Student;

public interface StudentServiceI 
{

	Student saveStudent(Student stu);

	 List<Student> getAllStudent();

	Optional<Student> getStudentById(int sid);

	void deleteStudent(int sid);

}
