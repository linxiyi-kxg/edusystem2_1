  
    /**    
    * @Title: ClassInfoCatalogFactoryMySQL.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package model;

/**  
    * @ClassName: ClassInfoCatalogFactoryMySQL  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public class ClassInfoCatalogFactoryMySQL extends ClassInfoCatalogFactory {
	  
	    /*   
	    *   
	    *   
	    * @return  
	    * @see model.ClassInfoCatalogFactory#createClassInfoCatalog()  
	    */  
	    
	@Override
	public  ClassInfoCatalog createClassInfoCatalog() {
		return new ClassInfoCatalogMySQL();
	}
}
