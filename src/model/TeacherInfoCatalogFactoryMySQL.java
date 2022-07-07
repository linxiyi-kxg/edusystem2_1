package model;

public class TeacherInfoCatalogFactoryMySQL extends TeacherInfoCatalogFactory {

	@Override
	public TeacherInfoCatalog createTeacherInfoCatalog(int teacherId) {
		return new TeacherInfoCatalogMySQL(teacherId);
	}
	
}
