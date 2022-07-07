package model;

import java.util.ArrayList;

public abstract class TeacherClassCatalog {
    public int teacherId;
    public TeacherClassCatalog(int teacherId) {
		// TODO 自动生成的构造函数存根
    	this.teacherId = teacherId;
	}
	public abstract ArrayList<String> getTeacherClass();
       
}
