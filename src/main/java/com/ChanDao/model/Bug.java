package com.ChanDao.model;

import java.sql.Timestamp;

/**
 * Author ajiang
 */

public class Bug {

	private String product;
	private String project;
	private String title;
	private int severity;
	private int pri;
	private String status;
	private int confirmed;
	private int activatedCount;
	private java.sql.Timestamp openedDate;
	private java.sql.Timestamp assignedDate;
	private String assignedTo;

	public Timestamp getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Timestamp assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void display() {
		System.out.println( "Bug{" +
				"product='" + product + '\'' +
				", project='" + project + '\'' +
				", title='" + title + '\'' +
				", severity=" + severity +
				", pri=" + pri +
				", status='" + status + '\'' +
				", confirmed=" + confirmed +
				", activatedCount=" + activatedCount +
				", openedDate=" + openedDate +
				", assignedDate=" + assignedDate +
				", assignedTo=" + assignedTo +
				'}');
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public int getPri() {
		return pri;
	}

	public void setPri(int pri) {
		this.pri = pri;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getActivatedCount() {
		return activatedCount;
	}

	public void setActivatedCount(int activatedCount) {
		this.activatedCount = activatedCount;
	}

	public java.sql.Timestamp getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(java.sql.Timestamp openedDate) {
		this.openedDate = openedDate;
	}

	public Bug(){

	}

	public Bug(String product, String project, String title, int severity, int pri, String status, int confirmed, int activatedCount, java.sql.Timestamp openedDate,java.sql.Timestamp assignedDate, String assignedTo) {
		this.product = product;
		this.project = project;
		this.title = title;
		this.severity = severity;
		this.pri = pri;
		this.status = status;
		this.confirmed = confirmed;
		this.activatedCount = activatedCount;
		this.openedDate = openedDate;
		this.assignedDate = assignedDate;
		this.assignedTo = assignedTo;
	}
}
