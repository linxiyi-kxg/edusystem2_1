package model;

public class TeacherClassCatalogFactoryMySQL extends TeacherClassCatalogFactory {

	@Override
	public TeacherClassCatalog createTeacherClassCatalog(int teacherId) {
		return new TeacherClassCatalogMySQL(teacherId);
	}
	
}
