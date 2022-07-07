package model;

public class StudentTrackCatalogFactoryMySQL extends StudentTrackCatalogFactory {

	@Override
	public StudentTrackCatalog createStudentTrackCatalog(int studentId) {
		// TODO 自动生成的方法存根
		return new StudentTrackCatalogMySQL(studentId);
	}

}
