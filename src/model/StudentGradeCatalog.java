package model;

import java.util.ArrayList;

public abstract class StudentGradeCatalog {
	public int studentId;
	public StudentGradeCatalog(int studentId) {
		this.studentId = studentId;
	}
    public abstract ArrayList<ArrayList<Integer>> getStudentGradeInfo(int n);
    	 
}
