package model;

import java.util.ArrayList;

public abstract class StudentAttendanceCatalog {
	public int studentId;
	public StudentAttendanceCatalog(int studentId) {
		this.studentId = studentId;
	}
	public abstract ArrayList<Integer> getStudentAttendanceInfo(int n);
}
