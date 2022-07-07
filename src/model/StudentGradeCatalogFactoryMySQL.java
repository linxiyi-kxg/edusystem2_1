package model;

public class StudentGradeCatalogFactoryMySQL extends StudentGradeCatalogFactory {

	@Override
	public StudentGradeCatalog createStudentGradeCatalog(int studentId) {
		// TODO 自动生成的方法存根
		return new StudentGradeCatalogMySQL(studentId);
	}

}
