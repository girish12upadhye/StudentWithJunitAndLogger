 package com.infy.StudentwithJunitAndLogger.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student 
{
	@Id
	private int sid;
	private String sname;
	private String saddr;
}
