package model;

import java.util.ArrayList;

public abstract class StudentTrackCatalog {
	public int studentId;
	public StudentTrackCatalog(int studentId) {
		this.studentId = studentId;
	}
	public abstract ArrayList<Integer> getStudentTrackInfo(int n);
}
