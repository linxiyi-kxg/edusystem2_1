  
    /**    
    * @Title: GradeInfoCatalogMySQL.java  
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
    * @ClassName: GradeInfoCatalogMySQL  
    * @Description: TODO  
    * @author Ariana  
    * @date 2022��7��1��  
    *    
    */

public class GradeInfoCatalogMySQL extends GradeInfoCatalog {
	  
	    /*   
	    *   
	    *   
	    * @param timeID  
	    * @see model.GradeInfoCatalog#checkGradeAttendance(int)  
	    */  
	    
	@Override
	public ArrayList<ArrayList<String>> checkGradeAttendance(ArrayList<String> timestamp) {
		ArrayList<ArrayList<String>> selectALLClassAttendanceInfo = new ArrayList<ArrayList<String>>();
		String filepath = "model/JDBC.properties";
		ReadProperties readProperties = new ReadProperties(filepath);
		
		try {
			PreparedStatement preparedStatement = null;
			ArrayList<String> countnum = new ArrayList<String>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
			String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
			String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
		    Connection connection = DriverManager.getConnection(url, user, password);
		    String sql1 = "SELECT 班级ID, COUNT(学生ID) AS 缺勤人数\r\n"
					+ "					FROM 学生班级表 AS c LEFT JOIN 学生考勤表 AS a USING(学生ID)\r\n"
					+ "					INNER JOIN 考勤类型表 AS t USING(考勤类型ID)\r\n"
					+ "					WHERE DATE_FORMAT(时间,'%Y-%m-%d %H:%I') BETWEEN DATE_FORMAT('" + timestamp.get(0) + "','%Y-%m-%d %H:%I') AND DATE_FORMAT('" + timestamp.get(1) + "','%Y-%m-%d %H:%I')\r\n"
					+ "					GROUP BY 班级ID\r\n"
					+ "					ORDER BY 班级ID";
		    String sql2 = "SELECT 考勤名称, COUNT(学生ID) AS 缺勤人数\r\n"
					+ "					FROM 学生班级表 AS c LEFT JOIN 学生考勤表 AS a USING(学生ID)\r\n"
					+ "					INNER JOIN 考勤类型表 AS t USING(考勤类型ID)\r\n"
					+ "					WHERE DATE_FORMAT(时间,'%Y-%m-%d %H:%I') BETWEEN DATE_FORMAT('" + timestamp.get(0) + "','%Y-%m-%d %H:%I') AND DATE_FORMAT('" + timestamp.get(1) + "','%Y-%m-%d %H:%I')\r\n"
					+ "					GROUP BY 考勤名称\r\n"
					+ "					ORDER BY 缺勤人数 DESC";
		    for (int i = 1; i <= 2; i++) {		
		    	if (i == 1) {
		    		preparedStatement = connection.prepareStatement(sql1);
		    	} else {
		    		preparedStatement = connection.prepareStatement(sql2);
		    	}
	
				ResultSet rs = preparedStatement.executeQuery();
				
				String num = null; //缺勤人数
				String id = null; //班级id
				int count = 0;
				while (rs.next()) {
					//写入Arraylist数组中
		        	ArrayList<String> onerow = new ArrayList<String>();	// 创建一维数组输入
		        	
		        	if ( i == 1) {
		        		id = rs.getString("班级ID"); //获取班级ID
			        	num = rs.getString("缺勤人数"); //获取缺勤人数
			        	count++;
		        	} else {
		        		id = rs.getString("考勤名称"); //获取班级ID
			        	num = rs.getString("缺勤人数"); //获取缺勤人数
			        	count++;
		        	}
					//写入数组
					onerow.add(id);
					onerow.add(num);
					

					selectALLClassAttendanceInfo.add(onerow);
	        	}
				countnum.add(Integer.toString(count));
	        	rs.close();
		    }
		    selectALLClassAttendanceInfo.add(0, countnum);
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return selectALLClassAttendanceInfo;
	}
	  
	    /*   
	    *   
	    *   
	    * @param subjectID
	    * @param trackID  
	    * @see model.GradeInfoCatalog#checkGradeLearning(int, int)  
	    */  
	    
	@Override
	public ArrayList<ArrayList<String>> checkGradeLearning(int subjectID, int trackID) {
		ArrayList<ArrayList<String>> selectALLStuGradeInfo = new ArrayList<ArrayList<String>>();
		String filepath = "model/JDBC.properties";
		ReadProperties readProperties = new ReadProperties(filepath);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
			String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
			String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
		    Connection connection = DriverManager.getConnection(url, user, password);
		    String sql = "SELECT AVG(考试成绩) AS 平均分, MAX(考试成绩) AS 最高分, MIN(考试成绩) AS 最低分 FROM 学生成绩表 AS grade, 学生班级表 AS class\r\n"
					+ "					WHERE grade.学生ID = class.学生ID and grade.学期ID = class.学期ID and 学科ID = " + subjectID + " \r\n"
					+ "					GROUP BY 考试开始时间, 学科ID, grade.学期ID, 考试类型ID\r\n"
					+ "					order by  考试开始时间 desc\r\n"
					+ "					LIMIT 0," + trackID ;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        
			ResultSet rs = preparedStatement.executeQuery(); //查询操作，返回ResultSet
	
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
				
        		selectALLStuGradeInfo.add(onerow);
        		
        	}
        	rs.close();			
			
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return selectALLStuGradeInfo;
	}
}
