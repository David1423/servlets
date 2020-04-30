package com.dav.service;

import java.util.ArrayList;
import java.util.List;

import com.dav.bo.StudentBo;
import com.dav.dao.StudentDAO;
import com.dav.dao.StudentDAOImpl;
import com.dav.vo.StudentVO;

public class StudentServiceImpl implements StudentService {

	@Override
	public List<StudentVO> getStudentsByCourse(String course) throws Exception {
		
		List<StudentBo> listBO = null;
		List<StudentVO> listVO = null;
		
		StudentVO vo = null;
		//StudentBo bo = null;
		StudentDAO dao = null;
		
		//create dao object
		dao = new StudentDAOImpl();
		
		//call method of DAO to fetch students
		listBO = dao.fetchStudents(course);
		//create a list of StudentVO object
		listVO = new ArrayList<StudentVO>();
		
		//convert BO to VO
		for(StudentBo st_BO:listBO){
			
			//create VO Object
			vo = new StudentVO();
			
			vo.setSno(String.valueOf(st_BO.getSno()));
			vo.setSname(st_BO.getSname());
			vo.setScourse(st_BO.getScourse());
			vo.setSfee(st_BO.getSfee());
			//add vo obj to List Collection
			listVO.add(vo);
		}
		
		return listVO;
	}

}
