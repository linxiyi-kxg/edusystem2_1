package controller;

import java.util.ArrayList;

import model.ClassGradeCatalog;
import model.ClassGradeCatalogFactory;
import model.ClassMemberCatalog;
import model.ClassMemberCatalogFactory;
import model.StudentAttendanceCatalog;
import model.StudentAttendanceCatalogFactory;
import model.StudentAttendanceCatalogFactoryMySQL;
import model.StudentGradeCatalog;
import model.StudentGradeCatalogFactory;
import model.StudentGradeCatalogFactoryMySQL;
import model.StudentInfoCatalog;
import model.StudentInfoCatalogFactory;
import model.StudentInfoCatalogFactoryMySQL;
import model.StudentTrackCatalog;
import model.StudentTrackCatalogFactory;
import model.StudentTrackCatalogFactoryMySQL;
import model.TeacherClassCatalog;
import model.TeacherClassCatalogFactory;
import model.TeacherInfoCatalog;
import model.TeacherInfoCatalogFactory;

public class TeacherController {
	private ArrayList<Integer> classMemberInformation; 

	private StudentInfoCatalog studentInfoCatalog;
	private StudentInfoCatalogFactory studentInfoCatalogFactory;
	
	private StudentTrackCatalog studentTrackCatalog;
	private StudentTrackCatalogFactory studentTrackCatalogFactory;
	
	private StudentAttendanceCatalog studentAttendanceCatalog;
	private StudentAttendanceCatalogFactory studentAttendanceCatalogFactory;
	
	private StudentGradeCatalog studentGradeCatalog;
	private StudentGradeCatalogFactory studentGradeCatalogFactory;

	private TeacherInfoCatalog teacherInfoCatalog;
	private TeacherInfoCatalogFactory teacherInfoCatalogFactory;
	
	private TeacherClassCatalog teacherClassCatalog;
	private TeacherClassCatalogFactory teacherClassCatalogFactory;
	
	private ClassMemberCatalog classMemberCatalog;
	private ClassMemberCatalogFactory classMemberCatalogFactory;
	
	private ClassGradeCatalog classGradeCatalog;
	private ClassGradeCatalogFactory classGradeCatalogFactory;

	public TeacherController(TeacherInfoCatalogFactory teacherInfoCatalogFactory, TeacherClassCatalogFactory teacherClassCatalogFactory, ClassMemberCatalogFactory classMemberCatalogFactory, ClassGradeCatalogFactory classGradeCatalogFactory) {
		// TODO ?????????????????????????????????
		//???????????????????????????
		this.teacherInfoCatalogFactory = teacherInfoCatalogFactory;
		this.teacherClassCatalogFactory = teacherClassCatalogFactory;
		this.classMemberCatalogFactory = classMemberCatalogFactory;
		this.classGradeCatalogFactory = classGradeCatalogFactory;
	}
	
	//????????????????????????
	public ArrayList<String> makeNewCheckInfo(int teacherId) {
        this.teacherInfoCatalog = this.teacherInfoCatalogFactory.createTeacherInfoCatalog(teacherId);
        return teacherInfoCatalog.getTeacherInfo();	 
	}
	
	public void endNewCheckInfo() {

	}
	
	//????????????????????????
	public ArrayList<String> makeNewCheckClass(int teacherId) {
		this.teacherClassCatalog = this.teacherClassCatalogFactory.createTeacherClassCatalog(teacherId);
		return teacherClassCatalog.getTeacherClass(); 
	}
	
	public void endNewCheckClass() {

	}

    //??????????????????????????????
	public ArrayList<String> makeNewCheckClassMember(int teacherId) {
		this.classMemberCatalog = this.classMemberCatalogFactory.createClassMemberCatalog(teacherId);
		return classMemberCatalog.getClassMember(); 
	}
	
	public void endNewCheckClassMember() {

	}

	//????????????????????????
	public void makeCheckClassGrade(int teacherId) {
        this.classGradeCatalog = this.classGradeCatalogFactory.createClassGradeCatalog(teacherId);
		this.classGradeCatalog.teacherId = teacherId;	 
	}

	public ArrayList<ArrayList<Integer>> chooseClassGradeType(ArrayList<String> gradeId) {
		return classGradeCatalog.getClassGrade(gradeId);
	}

	public void endCheckClassGrade() {

	}

    //????????????????????????
	public void makeCheckStudentInfo(int teacherId) {
		this.classMemberCatalog = this.classMemberCatalogFactory.createClassMemberCatalog(teacherId);
		this.classMemberInformation = this.classMemberCatalog.getClassMemberId();
	}
	
	//??????????????????????????????
	public void chooseInformationType(int typeId) {
		switch (typeId) {
		case 10:
			this.studentInfoCatalogFactory = new StudentInfoCatalogFactoryMySQL();
			break;
		case 11:
			this.studentTrackCatalogFactory = new StudentTrackCatalogFactoryMySQL();
			break;
		case 12:
			this.studentAttendanceCatalogFactory = new StudentAttendanceCatalogFactoryMySQL();
			break;
		case 13:
			this.studentGradeCatalogFactory = new StudentGradeCatalogFactoryMySQL();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + typeId);
		}

	}
	
	public ArrayList<String> checkStudentPersonalInfo(int studentId, int infoId) {
		if (this.classMemberInformation.contains(studentId)) {
			this.studentInfoCatalog = this.studentInfoCatalogFactory.createStudentInfoCatalog(studentId);
			return this.studentInfoCatalog.getStudentInfo();
		} else {
			return null;
		}
	}

    //??????????????????????????????
	public ArrayList<Integer> checkStudentPersonalTrack(int studentId, int trackId) {
		if (this.classMemberInformation.contains(studentId)) {
			this.studentTrackCatalog = this.studentTrackCatalogFactory.createStudentTrackCatalog(studentId);
			return this.studentTrackCatalog.getStudentTrackInfo(trackId);
		} else {
			return null;
		}
	}

    //??????????????????????????????
	public ArrayList<Integer> checkStudentPersonalAttendance(int studentId, int attendanceId) {
		if (this.classMemberInformation.contains(studentId)) {
			this.studentAttendanceCatalog = this.studentAttendanceCatalogFactory.createAttendanceCatalog(studentId);
			return this.studentAttendanceCatalog.getStudentAttendanceInfo(attendanceId);
		} else {
			return null;
		}
	}

    //??????????????????????????????
	public ArrayList<ArrayList<Integer>> checkStudentPersonalGrade(int studentId, int gradeId) {
		if (this.classMemberInformation.contains(studentId)) {
			this.studentGradeCatalog = this.studentGradeCatalogFactory.createStudentGradeCatalog(studentId);
			return studentGradeCatalog.getStudentGradeInfo(gradeId);
		} else {
			return null;
		}
	}

	//????????????????????????????????????
	public void end() {
		
	}

}
