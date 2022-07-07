package model;

import java.util.ArrayList;

public abstract class ClassMemberCatalog {
    public int teacherId;
	public abstract ArrayList<String> getClassMember();
	public abstract ArrayList<Integer> getClassMemberId();
    ClassMemberCatalog(int teacherId) {
    	this.teacherId = teacherId;
    }
}
