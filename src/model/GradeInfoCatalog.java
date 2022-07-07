  
    /**    
    * @Title: GradeInfoCatalog.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package model;

import java.util.ArrayList;

/**  
    * @ClassName: GradeInfoCatalog  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public abstract class GradeInfoCatalog {
	  
	    /**  
	    * @Title: checkGradeAttendance  
	    * @Description:  Get the attendance information by timeID.
	    * @param timeID:int
	    * @return void
	    * @throws  
	    */  
	    
	public abstract ArrayList<ArrayList<String>> checkGradeAttendance(ArrayList<String> timestamp);
	  
	    /**  
	    * @Title: checkGradeLearning  
	    * @Description:  Get the learning situation by subjectID, trackID.
	    * @param subjectID:int
	    * @param trackID:int
	    * @return void
	    * @throws  
	    */  
	    
	public abstract ArrayList<ArrayList<String>> checkGradeLearning(int subjectID, int trackID);
}
