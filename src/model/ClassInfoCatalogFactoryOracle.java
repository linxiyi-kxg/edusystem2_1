  
    /**    
    * @Title: ClassInfoCatalogFactoryOracle.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022年7月1日  
    * @version V1.0    
    */  
    
package model;

  
    /**  
    * @ClassName: ClassInfoCatalogFactoryOracle  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022年7月1日  
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
