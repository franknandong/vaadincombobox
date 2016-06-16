/**
 * 
 */
package com.fndong.combobox.domain;

/**
 * @author frank
 *
 */
public class RefState {
	 private String stateid;
	 private String statename;

	/**
	 * 
	 */
	public RefState() {
		// TODO Auto-generated constructor stub
	}
	
	public RefState(String stateid, String statename) {
		super();
		this.stateid = stateid;
		this.statename = statename;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}
	
	

}
