package model;

import java.util.ArrayList;

public abstract class TeacherInfoCatalog {
    public int teacherId;
	public abstract ArrayList<String> getTeacherInfo();
    TeacherInfoCatalog(int teacherId){
           this.teacherId=teacherId;
    }
	public abstract ArrayList<String> addTeacherInfo(ArrayList<String> teacherInfo);
}
