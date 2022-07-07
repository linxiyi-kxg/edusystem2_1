package controller;

import java.util.ArrayList;

import model.StudentAttendanceCatalog;
import model.StudentAttendanceCatalogFactory;
import model.StudentConsumptionCatalog;
import model.StudentConsumptionCatalogFactory;
import model.StudentGradeCatalog;
import model.StudentGradeCatalogFactory;
import model.StudentInfoCatalog;
import model.StudentInfoCatalogFactory;
import model.StudentTrackCatalog;
import model.StudentTrackCatalogFactory;

public class ParentsController {
	
	private StudentInfoCatalog studentInfoCatalog;
	private StudentInfoCatalogFactory studentInfoCatalogFactory;
	
	private StudentTrackCatalog studentTrackCatalog;
	private StudentTrackCatalogFactory studentTrackCatalogFactory;
	
	private StudentAttendanceCatalog studentAttendanceCatalog;
	private StudentAttendanceCatalogFactory studentAttendanceCatalogFactory;
	
	private StudentConsumptionCatalog studentConsumptionCatalog;
	private StudentConsumptionCatalogFactory studentConsumptionCatalogFactory;
	
	private StudentGradeCatalog studentGradeCatalog;
	private StudentGradeCatalogFactory studentGradeCatalogFactory;
	
	public ParentsController(StudentInfoCatalogFactory studentInfoCatalogFactory, StudentTrackCatalogFactory studentTrackCatalogFactory, StudentAttendanceCatalogFactory studentAttendanceCatalogFactory, StudentConsumptionCatalogFactory studentConsumptionCatalogFactory, StudentGradeCatalogFactory studentGradeCatalogFactory) {
		// TODO 自动生成的构造函数存根
		//创建相应的工厂对象
		this.studentInfoCatalogFactory = studentInfoCatalogFactory;
		this.studentAttendanceCatalogFactory = studentAttendanceCatalogFactory;
		this.studentConsumptionCatalogFactory = studentConsumptionCatalogFactory;
		this.studentGradeCatalogFactory = studentGradeCatalogFactory;
		this.studentTrackCatalogFactory = studentTrackCatalogFactory;
	}
	
	//查询学生信息
	public void makeNewCheckInfo(int studentId) {
		this.studentInfoCatalog = this.studentInfoCatalogFactory.createStudentInfoCatalog(studentId);
		
	}
	
	public ArrayList<String> chooseInfoType(int infoId) {
		return this.studentInfoCatalog.getStudentInfo();
	}
	
	public void endNewCheckInfo() {
		
	}
	
	//查询成长档案
	public void makeCheckTrackInfo(int studentId) {
		this.studentTrackCatalog = this.studentTrackCatalogFactory.createStudentTrackCatalog(studentId);
	}
	
	public ArrayList<Integer> chooseTrackType(int trackId) {
		
		return this.studentTrackCatalog.getStudentTrackInfo(trackId);
	}
	
	public void endCheckStudentTrackInfo() {
		
	}
	
	//查询学生考勤
	public void makeCheckStudentAttendance(int studentId) {
		this.studentAttendanceCatalog = this.studentAttendanceCatalogFactory.createAttendanceCatalog(studentId);
		this.studentAttendanceCatalog.studentId = studentId;
	}
	
	public ArrayList<Integer> chooseAttendanceType(int attendanceId) {
		return this.studentAttendanceCatalog.getStudentAttendanceInfo(attendanceId);
	}
	
	public void endCheckStudentAttendanceInfo() {
		
	}
	
	//查询学生消费记录
	public void makeCheckStudentConsumption(int studentId) {
		this.studentConsumptionCatalog = this.studentConsumptionCatalogFactory.createConsumptionCatalog(studentId);
	}
	
	public ArrayList<Double> chooseConsumptionType(int consumptionId) {
		return this.studentConsumptionCatalog.getStudentConsumptioninfo(consumptionId);
	}
	
	public void endCheckStudentConsumptionInfo() {
		
	}
	
	//查看学业画像
	public void makeCheckStudentGradeInfo(int studentId) {
		this.studentGradeCatalog = this.studentGradeCatalogFactory.createStudentGradeCatalog(studentId);
	}
	
	public ArrayList<ArrayList<Integer>> chooseGradeType(int gradeId) {
		return studentGradeCatalog.getStudentGradeInfo(gradeId);
	}
	
	public void endCheckStudentGradeInfo() {
		
	}
}
