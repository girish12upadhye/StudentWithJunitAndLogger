package com.infy.StudentwithJunitAndLogger.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> 
{
	private int status;
	private String message;
	private T responseData;
}
