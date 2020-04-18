package com.dav.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.dav.bo.StudentBo;

public class StudentDAO {
	
	private static final String INSERT_STUDENT = "insert into Student(sname,scourse,sfee)values(?,?,?);";
	
	InitialContext ic=null;
	DataSource ds = null;
	Connection con= null;
	PreparedStatement ps =null;
	
	int count=0;
	
	
	public Connection getPooledConnection() throws Exception {
		
		ic=new InitialContext();
		ds= (DataSource)ic.lookup("java:comp/env/DsJndi");
		return ds.getConnection();
	}
	
	public int insert(StudentBo bo) throws Exception{
		
		//prepare the pre-compiled query
		con=getPooledConnection();
		ps = con.prepareStatement(INSERT_STUDENT);
		
		ps.setString(1, bo.getSname());
		ps.setString(2, bo.getScourse());
		ps.setString(3, bo.getSfee());
		
		//send and execute query
		count = ps.executeUpdate();
		return count;
	}
	
	

}
