  
    /**    
    * @Title: ClassInfoCatalogFactoryOracle.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package model;

  
    /**  
    * @ClassName: ClassInfoCatalogFactoryOracle  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public abstract class ClassInfoCatalogFactoryOracle extends ClassInfoCatalogFactory{
	  
	    /*   
	    *   
	    *   
	    * @return  
	    * @see model.ClassInfoCatalogFactory#createClassInfoCatalog()  
	    */  
	    
	@Override
	public abstract ClassInfoCatalog createClassInfoCatalog();
}
