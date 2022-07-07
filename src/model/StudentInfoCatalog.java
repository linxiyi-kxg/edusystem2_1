package model;

import java.util.ArrayList;

public abstract class StudentInfoCatalog {
	public int studentId;
	StudentInfoCatalog(int studentId) {
		// TODO 自动生成的构造函数存根
		this.studentId = studentId;
	}
	public abstract ArrayList<String> getStudentInfo();
	public abstract ArrayList<String> addStudentInfo(ArrayList<String> studentInfo);
}
