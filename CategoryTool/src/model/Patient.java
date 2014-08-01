package model;

import java.util.Date;

public class Patient {
	
	public Patient(){}
	
	public Patient(String name, int screening_category, int diagnostic_category, int ultrasound, int biopsy, Date date){
		this.deleted = false;
		this.added = false;
		this.name = name;
		this.screening_category = screening_category;
		this.diagnostic_category = diagnostic_category;
		this.ultrasound = ultrasound;
		this.biopsy_result = biopsy;
		this.date = date;
	}
	
	private Boolean deleted;
	private Boolean added;
	private String name;
	private int screening_category;
	private int diagnostic_category;
	private int ultrasound;
	private Date date;
	private int biopsy_result;
	
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean selected) {
		this.deleted = selected;
	}
	
	public Boolean getAdded() {
		return added;
	}
	public void setAdded(Boolean added) {
		this.added = added;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScreening_category() {
		return screening_category;
	}
	public void setScreening_category(int screening_category) {
		this.screening_category = screening_category;
	}
	
	public int getDiagnostic_category() {
		return diagnostic_category;
	}
	public void setDiagnostic_category(int diagnostic_category) {
		this.diagnostic_category = diagnostic_category;
	}

	public int getUltrasound() {
		return ultrasound;
	}
	public void setUltrasound(int ultrasound) {
		this.ultrasound = ultrasound;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public void setBiopsy_result(int biopsy_result) {
		this.biopsy_result = biopsy_result;
	}

	public int getBiopsy_result() {
		return biopsy_result;
	}

}
