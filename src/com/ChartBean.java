package com.fid.collab;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChartBean {
	private String groupName;
	private int total;
	private int cmCount;

	public int getCmCount() {
		return cmCount;
	}

	public void setCmCount(int cmCount) {
		this.cmCount = cmCount;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
