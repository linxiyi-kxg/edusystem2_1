  
    /**    
    * @Title: GradeInfoCatalogFactoryOracle.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022年7月1日  
    * @version V1.0    
    */  
    
package model;

  
    /**  
    * @ClassName: GradeInfoCatalogFactoryOracle  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022年7月1日  
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
