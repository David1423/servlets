package com.dav.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.dav.bo.StudentBo;

public class StudentDAOImpl implements StudentDAO {

	private static final String GET_STUDENTS_BY_COURSE = "SELECT sno,sname,scourse,sfee FROM Student WHERE scourse=?";

	private static Connection getPooledConnection() throws Exception {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;

		try {
			// get InitialContext Object
			ic = new InitialContext();
			// Get DataSource Object for through Dependency Lookup Operation
			ds = (DataSource) ic.lookup("java:comp/env/DsJndi");
			// get connection object from server managed connection pool
			con = ds.getConnection();
			// return connection Object
			return con;
		} catch (Exception e) {
			throw e;
		}

	}

	public List<StudentBo> fetchStudents(String course) throws Exception {

		List<StudentBo> listBo = null;
		StudentBo bo = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			// get connection Object
			con = getPooledConnection();
			// prepare statement
			ps = con.prepareStatement(GET_STUDENTS_BY_COURSE);
			// set query param
			ps.setString(1, course);
			// send and Execute Query
			rs = ps.executeQuery();

			if (rs != null) {
				//create list obj of StudentBo object
				listBo = new ArrayList<StudentBo>();
				while (rs.next()) {
					// create new BO object
					bo = new StudentBo();

					// set values to bo object received from DB
					bo.setSno(rs.getInt(1));
					bo.setSname(rs.getString(2));
					bo.setScourse(rs.getString(3));
					bo.setSfee(rs.getString(4));

					// add bo to List collection
					listBo.add(bo);

				} // while
			} // if
		} // try
		catch (SQLException se) {
			throw se; // exception re-throwing (industry standard)
		} catch (Exception e) {
			throw e; // exception re-throwing (industry standard)
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return listBo;
	}// method
}// class
