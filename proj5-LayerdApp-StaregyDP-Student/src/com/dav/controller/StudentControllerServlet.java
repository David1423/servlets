package com.dav.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dav.service.StudentServiceImpl;
import com.dav.vo.StudentVO;

@WebServlet("/studenturl")
public class StudentControllerServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter pw = null;
		String course = null;
		StudentServiceImpl service = null;
		List<StudentVO> listVO = null;
		int stdCount = 0;

		try {
			// get PrintWrite object
			pw = res.getWriter();
			// get the req param from the form or client
			course = req.getParameter("course");
			// get Service Object
			service = new StudentServiceImpl();
			// fetch the Students of the course
			listVO = service.getStudentsByCourse(course);
			pw.println("<h1 style='color:red;text-align:center;'>Result page</h1>");
			if (listVO != null && listVO.size() != 0) {
				pw.println("<h2 style='color:green;text-align:center;'>List of Students with course :: <b style='color:blue'>"+course.toUpperCase()+"</b></h2>");
				pw.println("<br><table bgcolor='gray' border='1' align='center'>");
				pw.println("<tr bgcolor='pink'><th>Sr.No</th><th>ST.NO</th><th>NAME</th><th>COURSE FEE</th></tr>");
				for (StudentVO vo : listVO) {
					pw.println("<tr><td>" + (++stdCount) + "</td>");
					pw.println("<td>" + vo.getSno() + "</td>");
					pw.println("<td>" + vo.getSname() + "</td>");
					/*pw.println("<td>" + vo.getScourse() + "</td>");*/
					pw.println("<td>" + vo.getSfee() + "</td>");
					pw.println("</tr>");
				} // for
				pw.println("</table>");
			} // if
			else {
				pw.println("<br><h2 style='color:red;text-align:center;border:1px'>No Students have taken the course ::" + course+ "</h2>");
			} // else
		} // try
		catch (SQLException se) {
			se.printStackTrace();
			pw.println("Internal DB Problem");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("Internal Error Occured please Try again");
		} finally {
			pw.println("<p style='text-align:center'><a href='index.html' >H O M E</a></p>");

			if (pw != null)
				pw.close();
		} // finally

	}// doGet(-,-)

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}// doPost

}// class
