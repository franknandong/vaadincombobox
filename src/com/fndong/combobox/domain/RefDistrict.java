/**
 * 
 */
package com.fndong.combobox.domain;

/**
 * @author frank
 *
 */
public class RefDistrict {
	 private String refdistrictid;
	 private String refdistrictname;
	 private String stateid;
	/**
	 * 
	 */
	public RefDistrict() {
		// TODO Auto-generated constructor stub
	}
	
	public RefDistrict(String refdistrictid, String refdistrictname, String stateid) {
		super();
		this.refdistrictid = refdistrictid;
		this.refdistrictname = refdistrictname;
		this.stateid = stateid;
	}

	public String getRefdistrictid() {
		return refdistrictid;
	}
	public void setRefdistrictid(String refdistrictid) {
		this.refdistrictid = refdistrictid;
	}
	public String getRefdistrictname() {
		return refdistrictname;
	}
	public void setRefdistrictname(String refdistrictname) {
		this.refdistrictname = refdistrictname;
	}
	public String getStateid() {
		return stateid;
	}
	public void setStateid(String stateid) {
		this.stateid = stateid;
	}
	
	

}
