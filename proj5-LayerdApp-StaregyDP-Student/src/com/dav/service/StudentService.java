package com.dav.service;

import java.util.List;

import com.dav.bo.StudentBo;
import com.dav.vo.StudentVO;

public interface StudentService {
	
	public List<StudentVO> getStudentsByCourse(String course) throws Exception;

}
