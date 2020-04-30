package com.dav.dao;

import java.util.List;

import com.dav.bo.StudentBo;
import com.dav.dto.StudentDTO;

public interface StudentDAO {
	
	public List<StudentBo> fetchStudents(String course)throws Exception;

}
