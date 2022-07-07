package model;

public class StudentConsumptionCatalogFactoryMySQL extends StudentConsumptionCatalogFactory {

	@Override
	public StudentConsumptionCatalog createConsumptionCatalog(int studentId) {
		// TODO 自动生成的方法存根
		return new StudentConsumptionCatalogMySQL(studentId);
	}

}
