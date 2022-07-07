  
    /**    
    * @Title: ClassInfoCatalogMySQL.java  
    * @Package model  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    * @version V1.0    
    */  
    
package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import serve.ReadProperties;

/**  
    * @ClassName: ClassInfoCatalogMySQL  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public class ClassInfoCatalogMySQL extends ClassInfoCatalog {
	  
	    /*   
	    *   
	    *   
	    * @param classID
	    * @return  
	    * @see model.ClassInfoCatalog#checkClassInfo(int)  
	    */  
	    
	@Override
	public ArrayList<String> checkClassInfo(int classID) {
		ArrayList<String> selectClassStuName = new ArrayList<String>();
		String filepath = "model/JDBC.properties";
		ReadProperties readProperties = new ReadProperties(filepath);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
			String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
			String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
		    Connection connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT 学生姓名 FROM 学生表, 学生班级表 WHERE 学生表.学生ID = 学生班级表.学生ID AND 班级ID = " + classID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			String stuName = null; //姓名
		
			while (rs.next()) {
	        	stuName = rs.getString("学生姓名"); //获取姓名
				//写入数组
	        	selectClassStuName.add(stuName);
        	}
        	rs.close();
			
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return selectClassStuName;
	}
	  
	/*   
	*   
	*   
	* @param classID
	* @param subjectID
	* @param trackID  
	* @see model.ClassInfoCatalog#checkClassLearning(int, int, int)  
	*/  
	
	@Override
	public ArrayList<ArrayList<String>> checkClassLearning(int classID, int subjectID, int trackID) {
		ArrayList<ArrayList<String>> selectClassGradeInfo = new ArrayList<ArrayList<String>>();
		//转换类型后的数组
		String filepath = "model/JDBC.properties";
		ReadProperties readProperties = new ReadProperties(filepath);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
			String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
			String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
		    Connection connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT AVG(考试成绩) AS 平均分, MAX(考试成绩) AS 最高分, MIN(考试成绩) AS 最低分 FROM 学生成绩表 AS grade, 学生班级表 AS class\r\n"
					+ "					WHERE grade.学生ID = class.学生ID AND grade.学期ID = class.学期ID AND 班级ID = " + classID + " AND 学科ID = " + subjectID + "\r\n"
					+ "					GROUP BY 班级ID, 学科ID, grade.学期ID, 考试类型ID\r\n"
					+ "					ORDER BY  考试开始时间 DESC\r\n"
					+ "					LIMIT 0," + trackID;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery(); //查询操作，返回ResultSet
	
			Integer avg = null;
			Integer max = null;
			Integer min = null;
		
			while(rs.next()) {
				
				//写入Arraylist数组中
	        	ArrayList<String> onerow = new ArrayList<String>();	// 创建一维数组输入

				avg = rs.getInt("平均分");//获取平均分
				max = rs.getInt("最高分");//获取最高分
				min = rs.getInt("最低分");//获取最低分
				
				//将数据类型进行转换
				String avgs = Integer.toString(avg);
				String maxs = Integer.toString(max);
				String mins = Integer.toString(min);

				//写入数组
        		onerow.add(avgs);
        		onerow.add(maxs);
        		onerow.add(mins);
				
        		selectClassGradeInfo.add(onerow);
        		
        	}
        	rs.close();
			
			
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return selectClassGradeInfo;
	}
}
