package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TrainingCertification")
public class TrainingCertification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trainingID;
	private String trainingName;
	private String completionDate;
	private String expirationDate;
	public Integer getTrainingID() {
		return trainingID;
	}
	public void setTrainingID(Integer trainingID) {
		this.trainingID = trainingID;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
    
}
