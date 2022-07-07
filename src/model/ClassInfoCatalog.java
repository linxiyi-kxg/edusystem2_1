  
    /**    
    * @Title: ClassInfoCatalog.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package model;

import java.util.ArrayList;

/**  
    * @ClassName: ClassInfoCatalog  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public abstract class ClassInfoCatalog {
	  
	    /**  
	    * @Title: checkClassInfo  
	    * @Description: TODO  
	    * @param classID��int
	    * @return ArrayList<String>
	    * @throws  
	    */  
	    
	public abstract ArrayList<String> checkClassInfo(int classID);
	  
	    /**  
	    * @Title: checkClassLearning  
	    * @Description: TODO  
	    * @param classID: int
	    * @param subjectID: int
	    * @param trackID: int
	    * @return void
	    * @throws  
	    */  
	    
	public abstract ArrayList<ArrayList<String>> checkClassLearning(int classID, int subjectID, int trackID);
}
