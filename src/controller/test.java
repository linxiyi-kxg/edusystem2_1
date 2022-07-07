package controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import model.StudentAttendanceCatalogFactory;
import model.StudentAttendanceCatalogFactoryMySQL;
import model.StudentConsumptionCatalogFactory;
import model.StudentConsumptionCatalogFactoryMySQL;
import model.StudentGradeCatalogFactory;
import model.StudentGradeCatalogFactoryMySQL;
import model.StudentInfoCatalogFactory;
import model.StudentInfoCatalogFactoryMySQL;
import model.StudentTrackCatalogFactory;
import model.StudentTrackCatalogFactoryMySQL;

public class test {

	public static void main(String[] args) {
		StudentInfoCatalogFactory studentInfoCatalogFactory = new StudentInfoCatalogFactoryMySQL();
		StudentTrackCatalogFactory studentTrackCatalogFactory = new StudentTrackCatalogFactoryMySQL();
		StudentConsumptionCatalogFactory studentConsumptionCatalogFactory =  new StudentConsumptionCatalogFactoryMySQL();
		StudentAttendanceCatalogFactory studentAttendanceCatalogFactory = new StudentAttendanceCatalogFactoryMySQL();
		StudentGradeCatalogFactory studentGradeCatalogFactory = new StudentGradeCatalogFactoryMySQL();
		
		ParentsController parentsController = new ParentsController(studentInfoCatalogFactory, studentTrackCatalogFactory, studentAttendanceCatalogFactory, studentConsumptionCatalogFactory, studentGradeCatalogFactory);
		for(int i=0;i<2;i++) {
			System.out.println("sql" + i);
		}
		
		int studentId = 49;
		int infoId = 2;
		ArrayList<String> te = new ArrayList<String>();
//		test = parentsController.makeNewCheckInfo(studentId);
//		System.out.println(test);
		
		test t = new test();
		try {
			String key = t.ReadProperties("6");
			String classname = "controller.ParentsController";
			Class<?> class1 = Class.forName(classname);
			Constructor<?> constr = class1.getConstructor(StudentInfoCatalogFactory.class, StudentTrackCatalogFactory.class, StudentAttendanceCatalogFactory.class, StudentConsumptionCatalogFactory.class, StudentGradeCatalogFactory.class);
			Object object = constr.newInstance(studentInfoCatalogFactory, studentTrackCatalogFactory, studentAttendanceCatalogFactory, studentConsumptionCatalogFactory, studentGradeCatalogFactory);
			Method[] met = class1.getDeclaredMethods();
//			for(Method m : met) {
//				System.out.println(m);
//			}
			
			for (int i = 1; i <=Integer.parseInt(key); i++) {
				switch(i) {
				case 1 :{
					String method = t.ReadProperties("1" + "method" + i);
					Method me = class1.getDeclaredMethod(method, int.class);
					System.out.println(me);
					te = (ArrayList<String>) me.invoke(object, studentId);
					System.out.println(te);
					break;
				}
				case 2: {
					String method = t.ReadProperties("1" + "method" + i);
					Method me = class1.getDeclaredMethod(method, int.class);
					
					System.out.println(me);
					te = (ArrayList<String>) me.invoke(object, infoId);
					System.out.println(te);
					break;
				}
				case 3:{
					String method = t.ReadProperties("1" + "method" + i);
					Method me = class1.getDeclaredMethod(method);
					System.out.println(me);
					te = (ArrayList<String>) me.invoke(object);
					System.out.println(te);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + i);
				}
				
				
			}
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public String ReadProperties(String keyword) throws IOException {
		String filepath = "serve/config.properties";
		InputStream in = test.class.getClassLoader().getResourceAsStream(filepath);
		Properties properties = new Properties();
		properties.load(in);
		String key = properties.getProperty(keyword);
		in.close();
		in = null;
		return key;
	}

}
