package com.dav.dto;

import java.io.Serializable;

public class StudentDTO implements Serializable{
	

	private String sname;
	private String scourse;
	private String sfee;
	
	//setters  and Getters
	public void setSname(String sname) {
		this.sname = sname;
	}
	public void setScourse(String scourse) {
		this.scourse = scourse;
	}
	public void setSfee(String sfee) {
		this.sfee = sfee;
	}
	public String getSname() {
		return sname;
	}
	public String getScourse() {
		return scourse;
	}
	public String getSfee() {
		return sfee;
	}
	
	@Override
	public String toString() {
		return "StudentVo [sname=" + sname + ", scourse=" + scourse + ", sfee=" + sfee + "]";
	}
	
}
