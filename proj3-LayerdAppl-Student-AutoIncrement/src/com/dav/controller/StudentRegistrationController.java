package com.dav.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dav.dto.StudentDTO;
import com.dav.service.StudentRegService;
import com.dav.vo.StudentVo;

@WebServlet("/register")
public class StudentRegistrationController extends HttpServlet {

	String sname = null, scourse = null, sfee = null;
	int count;
	PrintWriter pw = null;

	StudentRegService service = null;

	public void init() {
		service = new StudentRegService();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		StudentVo vo = new StudentVo();

		// get PrintWriter Object
		 pw =res.getWriter();

		sname = req.getParameter("sName");
		scourse = req.getParameter("sCourse");
		sfee = req.getParameter("sFee");

		vo.setScourse(scourse);
		vo.setSname(sname);
		vo.setSfee(sfee);

		// convert VO to DTO

		StudentDTO dto = new StudentDTO();

		dto.setScourse(vo.getScourse());
		dto.setSname(vo.getSname());
		dto.setSfee(vo.getSfee());

		try {

			count = service.registerStudent(dto);
			
			pw.println("<head>\n" + 
					"<meta charset=\"UTF-8\">\n" + 
					"<title>Student-LayerdApp</title>\n" + 
					"<link rel=\"icon\" type=\"image/png\" href=\"favicon.png\" />\n" + 
					"</head>");
			

			if (count > 0) {
				pw.println("Student Registration Suceeded ");
			} else {

				pw.println("Student Registration Failed ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pw.println("<h2><a href='index.html'>HOME</a></h2>");	
		}
		
			
		

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
