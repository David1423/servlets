package com.dav.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class StudentSearchEngine extends HttpServlet {
	

	private static final String GET_STUDENT_BY_NAME = "SELECT name,school,grade from users where name=?;";

	// used to get the dataSource in jndi registry from any server directly without -
	// Initial Context Obj
	
	@Resource(name = "MysqlDsjndi")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// PrintWriter obj

		PrintWriter pw = null;
		String name = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		 //InitialContext ic =null;

		// get printWriter obj
		pw = res.getWriter();
		// set the content type
		res.setContentType("text/html");

		try {

			/*
			 * Class.forName("com.mysql.cj.jdbc.Driver");
			 * con=DriverManager.getConnection("jdbc:mysql://localhost:3306/LaTrobe","root",
			 * "root");
			 */
			
			/*
			 * ic = new InitialContext(); ds =(DataSource) ic.lookup("java:/MysqlDsjndi");
			 * con =ds.getConnection();
			 */
			 

			//get conneciton from the connection pool through DataSource object
			con = ds.getConnection();

			if (con != null) {
				System.out.println("connection Established.");
				System.out.println(ds.getClass());
			}

			name = req.getParameter("name");
			ps = con.prepareStatement(GET_STUDENT_BY_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			pw.println("<h1>STUDENTS WITH NAME: <h2 style='color:red'>" + name + "</h2> </h1>");

			if (rs != null) {
				while (rs.next()) {
					pw.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3) + "<br>");
				}
			} else {
				pw.println("<h1>NO Records Found</h1>");
			}
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			pw.println("<h2><a href='index.html'>HOME</a></h2>");
			pw.close();
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // finally
	}// doGet

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
