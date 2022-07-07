package model;

public class StudentInfoCatalogFactoryMySQL extends StudentInfoCatalogFactory {

	@Override
	public StudentInfoCatalog createStudentInfoCatalog(int studentId) {
		// TODO 自动生成的方法存根
		 
		 return new StudentInfoCatalogMySQL(studentId);
	}
	
}
