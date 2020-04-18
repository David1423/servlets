package com.dav.service;

import com.dav.bo.StudentBo;
import com.dav.dao.StudentDAO;
import com.dav.dto.StudentDTO;

public class StudentRegService {

	StudentDAO dao = null;
	StudentBo bo = null;
	int count = 0;

	public StudentRegService() {
		dao = new StudentDAO();
	}

	public int registerStudent(StudentDTO dto) throws Exception {
		
		// create BO Object
		bo = new StudentBo();

		bo.setSname(dto.getSname());
		bo.setScourse(dto.getScourse());
		bo.setSfee(dto.getSfee());

		// use dao
		count = dao.insert(bo);
		return count;
		
	}

}
