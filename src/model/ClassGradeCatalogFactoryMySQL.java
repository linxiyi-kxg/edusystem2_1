package model;

public class ClassGradeCatalogFactoryMySQL extends ClassGradeCatalogFactory {


	@Override
	public ClassGradeCatalog createClassGradeCatalog(int teacherId) {
		return new ClassGradeCatalogMySQL(teacherId);
	}

}
