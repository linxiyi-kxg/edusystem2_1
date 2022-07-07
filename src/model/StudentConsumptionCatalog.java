package model;

import java.util.ArrayList;

public abstract class StudentConsumptionCatalog {
	public int studentId;
	public StudentConsumptionCatalog(int studentId) {
		this.studentId = studentId;
	}
	public abstract ArrayList<Double> getStudentConsumptioninfo(int n);
}
