  
    /**    
    * @Title: GradeInfoCatalogFactoryMySQL.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package model;

  
    /**  
    * @ClassName: GradeInfoCatalogFactoryMySQL  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public class GradeInfoCatalogFactoryMySQL extends GradeInfoCatalogFactory {
	  
	    /*   
	    *   
	    *   
	    * @return  
	    * @see model.GradeInfoCatalogFactory#createGradeInfoCatalog()  
	    */  
	    
	@Override
	public GradeInfoCatalog createGradeInfoCatalog() {
		return new GradeInfoCatalogMySQL();
	}
}
