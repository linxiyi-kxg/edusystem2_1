  
    /**    
    * @Title: GradeInfoCatalogFactoryOracle.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package model;

  
    /**  
    * @ClassName: GradeInfoCatalogFactoryOracle  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public abstract class GradeInfoCatalogFactoryOracle extends GradeInfoCatalogFactory {
	  
	    /*   
	    *   
	    *   
	    * @return  
	    * @see model.GradeInfoCatalogFactory#createGradeInfoCatalog()  
	    */  
	    
	@Override
	public abstract GradeInfoCatalog createGradeInfoCatalog();
}
