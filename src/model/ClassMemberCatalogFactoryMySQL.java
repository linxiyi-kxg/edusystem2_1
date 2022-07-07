package model;

public class ClassMemberCatalogFactoryMySQL extends ClassMemberCatalogFactory {

	@Override
	public ClassMemberCatalog createClassMemberCatalog(int teacherId) {
		return new ClassMemberCatalogMySQL(teacherId);
	}
	
}
