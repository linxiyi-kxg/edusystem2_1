  
    /**    
    * @Title: AdministratorController.java  
    * @Package controller  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package controller;

import java.util.ArrayList;

import model.ClassInfoCatalog;
import model.ClassInfoCatalogFactory;
import model.GradeInfoCatalog;
import model.GradeInfoCatalogFactory;
import model.StudentInfoCatalog;
import model.StudentInfoCatalogFactory;
import model.TeacherInfoCatalog;
import model.TeacherInfoCatalogFactory;

/**  
    * @ClassName: AdministratorController  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public class AdministratorController {
	private StudentInfoCatalogFactory studentInfoCatalogFactory;
	private StudentInfoCatalog studentInfoCatalog;
	
	private TeacherInfoCatalogFactory teacherInfoCatalogFactory;
	private TeacherInfoCatalog teacherInfoCatalog;
	
	private ClassInfoCatalogFactory classInfoCatalogFactory;
	private ClassInfoCatalog classInfoCatalog;
	
	private GradeInfoCatalogFactory gradeInfoCatalogFactory;
	private GradeInfoCatalog gradeInfoCatalog;
	
	/**  
	 * AdministratorController.  
	 *    
	 */

	public AdministratorController(StudentInfoCatalogFactory studentInfoCatalogFactory, TeacherInfoCatalogFactory teacherInfoCatalogFactory, ClassInfoCatalogFactory classInfoCatalogFactory, GradeInfoCatalogFactory gradeInfoCatalogFactory) {
		// TODO Auto-generated constructor stub
		this.studentInfoCatalogFactory = studentInfoCatalogFactory;
		this.teacherInfoCatalogFactory = teacherInfoCatalogFactory;
		this.classInfoCatalogFactory = classInfoCatalogFactory;
		this.gradeInfoCatalogFactory = gradeInfoCatalogFactory;
	}
	  
	    /**  
	    * @Title: addStudentInfo  
	    * @Description: Add a student's basic information. 
	    * @param studentInfo: ArrayList<String>
	     * @return 
	    * @return void
	    * @throws  
	    */  
	    
	public ArrayList<String> addStudentInfo(ArrayList<String> studentInfo) {
		this.studentInfoCatalog = this.studentInfoCatalogFactory.createStudentInfoCatalog(Integer.parseInt(studentInfo.get(0)));
		return studentInfoCatalog.addStudentInfo(studentInfo);
	}
	  
	    /**  
	    * @Title: endAddStudentInfo  
	    * @Description: TODO  
	    * @param 
	    * @return void
	    * @throws  
	    */  
	    
	public void endAddStudentInfo() {
		
	}
	  
	    /**  
	    * @Title: addTeacherInfoCatalog  
	    * @Description: Add a teacher's basic information. 
	    * @param teacherInfo: ArrayList<String>
	     * @return 
	    * @return void
	    * @throws  
	    */  
	    
	public ArrayList<String> addTeacherInfoCatalog(ArrayList<String> teacherInfo) {
		this.teacherInfoCatalog = this.teacherInfoCatalogFactory.createTeacherInfoCatalog(Integer.parseInt(teacherInfo.get(0)));
		return teacherInfoCatalog.addTeacherInfo(teacherInfo);
	}
	  
	    /**  
	    * @Title: endAddTeacherInfo  
	    * @Description: TODO  
	    * @param 
	    * @return void
	    * @throws  
	    */  
	    
	public void endAddTeacherInfo() {
		
	}
	  
	    /**  
	    * @Title: checkClassInfo  
	    * @Description: Get the student name list of the class by classID.
	    * @param classID: int
	    * @return ArrayList<String>
	    * @throws  
	    */  
	    
	public ArrayList<String> checkClassInfo(int classID) {
		this.classInfoCatalog = this.classInfoCatalogFactory.createClassInfoCatalog();
		return classInfoCatalog.checkClassInfo(classID);
	}
	  
	    /**  
	    * @Title: endCheckClassInfo  
	    * @Description: TODO  
	    * @param 
	    * @return void
	    * @throws  
	    */  
	    
	public void endCheckClassInfo() {
		
	}
	  
	    /**  
	    * @Title: checkClassLearning  
	    * @Description: Get the learning situation of the class by classID, subjectID, trackID.
	    * @param classID:int
	    * @param subjectID:int
	    * @param trackID:int
	     * @return 
	     * @return 
	    * @return void
	    * @throws  
	    */  
	    
	public ArrayList<ArrayList<String>> checkClassLearning(int classID, int subjectID, int trackID) {
		this.classInfoCatalog = this.classInfoCatalogFactory.createClassInfoCatalog();
		return classInfoCatalog.checkClassLearning(classID, subjectID, trackID);
	}
	  
	    /**  
	    * @Title: endCheckClassLearning  
	    * @Description: TODO  
	    * @param 
	    * @return void
	    * @throws  
	    */  
	    
	public void endCheckClassLearning() {
		
	}
	  
	    /**  
	    * @Title: checkGradeAttendance  
	    * @Description: Get the attendance information by timeID.
	    * @param timeID: int
	     * @return 
	    * @return void
	    * @throws  
	    */  
	    
	public ArrayList<ArrayList<String>> checkGradeAttendance(ArrayList<String> timestamp) {
		this.gradeInfoCatalog = this.gradeInfoCatalogFactory.createGradeInfoCatalog();
		return gradeInfoCatalog.checkGradeAttendance(timestamp);
	}
	  
	    /**  
	    * @Title: endCheckGradeAttendance  
	    * @Description: TODO  
	    * @param 
	    * @return void
	    * @throws  
	    */  
	    
	public void endCheckGradeAttendance() {
		
	}
	  
	    /**  
	    * @Title: checkGradeLearning  
	    * @Description: Get the learning situation by subjectID, trackID.
	    * @param subjectID: int
	    * @param trackID: int
	     * @return 
	    * @return void
	    * @throws  
	    */  
	    
	public ArrayList<ArrayList<String>> checkGradeLearning(int subjectID, int trackID) {
		this.gradeInfoCatalog = this.gradeInfoCatalogFactory.createGradeInfoCatalog();
		return gradeInfoCatalog.checkGradeLearning(subjectID, trackID);
	}
	  
	    /**  
	    * @Title: endCheckGradeLearning  
	    * @Description: TODO  
	    * @param 
	    * @return void
	    * @throws  
	    */  
	    
	public void endCheckGradeLearning() {
		
	}
}
