package model;

import java.util.ArrayList;

public abstract class ClassGradeCatalog {
	public int teacherId;
	public abstract ArrayList<ArrayList<Integer>> getClassGrade(ArrayList<String> gradeId);
	ClassGradeCatalog(int teacherId) {
		this.teacherId = teacherId;
	}
    	 
}
