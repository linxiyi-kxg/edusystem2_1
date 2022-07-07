package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

import serve.ReadProperties;

public class ClassGradeCatalogMySQL extends ClassGradeCatalog {
	
	ClassGradeCatalogMySQL(int teacherId) {
		super(teacherId);
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	public ArrayList<ArrayList<Integer>> getClassGrade(ArrayList<String> gradeId) {
		//gradeId为一个ArrayList数组，包含三个参数，第一个参数为学期ID，第二个为查询最近的次数，第三个学科ID
		//查看班级学业信息
				ArrayList<ArrayList<Integer>> checkclassstudentgrade = new ArrayList<ArrayList<Integer>>();
				Connection conn = null;
				String driver = "com.mysql.cj.jdbc.Driver";
				
				//读取配置文件
				String filepath = "model/JDBC.properties";
				ReadProperties readProperties = new ReadProperties(filepath);
				
		        Integer runtime = Integer.valueOf(gradeId.get(1)); //将传入的时间转换为整型用于计算循环
		        
		        try {
		        	String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
					String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
					String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
					Class.forName(driver);
		        	//连接数据库
		        	conn = DriverManager.getConnection(url, user, password);
		        	if (!conn.isClosed()) {
		        		System.out.print("数据库连接成功!\n");
		        	}
		        	Statement statement = conn.createStatement();
		        	
		        	String timeString = null;
		        	//创建时间数组用于存储读取出来的time个时间
		        	ArrayList<String> timelist = new ArrayList<String>();
		        	
		        	//查询最新time次的时间
		        	String sqltime = "SELECT DISTINCT 考试开始时间 FROM 软工小组项目.学生成绩表 ORDER BY 考试开始时间 DESC LIMIT " + gradeId.get(1);
		        	
		        	//记录读取到的数据
		        	ResultSet rstime = statement.executeQuery(sqltime);
		        	
		        	while (rstime.next()) {
						timeString = rstime.getString("考试开始时间");
						timelist.add(timeString);
						
					}
		        	
		        	rstime.close();
		        	System.out.print(timelist + "\n");
		        	//通过循环对每个时间段进行查询
		        	for (int i = 0; i < runtime; i++) {
		        		//新建数组用于存储每一次读取出来的数据
		        		ArrayList<Integer> grade = new ArrayList<Integer>();
		        		
		        		String sql = "SELECT 考试成绩 FROM 软工小组项目.学生成绩表 \r\n"
		        				+ "WHERE 考试开始时间 = '" + timelist.get(i) + "' \r\n"
		        				+ "and 学期ID= " + gradeId.get(0) + " \r\n"
		        				+ "AND 学科ID = " + gradeId.get(2) + " \r\n"
		        				+ "AND 学生ID IN (\r\n"
		        				+ "SELECT 学生ID FROM 软工小组项目.学生班级表 \r\n"
		        				+ "WHERE 班级ID IN ( \r\n"
		        				+ "SELECT 班级ID FROM 软工小组项目.学生班级表 \r\n"
		        				+ "WHERE 班主任ID = " + this.teacherId + "))";
		    	
		        		ResultSet rs = statement.executeQuery(sql);
		    	
		        		//获取成绩
		        		Integer gradeInteger;
		        		
		        		//写入成绩
		        		while (rs.next()) {
		        			gradeInteger = rs.getInt("考试成绩");
		        			grade.add(gradeInteger);
		        		}
		        		List<Integer> grades = grade;
		        		IntSummaryStatistics statistics = grades.stream().mapToInt(Number::intValue).summaryStatistics();
		        		
		        		//创建当前成绩处理后的存储数组
		        		ArrayList<Integer> onetimegrade = new ArrayList<Integer>();
		        		
		        		//先判断是否当前时间段是否有成绩，没有成绩则写入0
		        		if (grade.size() != 0) {
							//计算平均值
		        			onetimegrade.add((int) statistics.getAverage());
		        			//计算最大值
		        			onetimegrade.add(statistics.getMax());
		        			//计算最小值
		        			onetimegrade.add(statistics.getMin());
						} else {
		        			//平均值，最大值，最小值为0
							onetimegrade.add(0);
							onetimegrade.add(0);
							onetimegrade.add(0);
						}
		        		
		        		//将计算后的结果写入二维数组中
		        		checkclassstudentgrade.add(onetimegrade);
		        		
		        		//关闭Sql执行语句
		        		rs.close();
						
					}
		        	System.out.println(checkclassstudentgrade);
		        	
		        	//关闭数据库链接
		        	conn.close();
				}
				catch (ClassNotFoundException e) {
					// TODO: handle exception
					//数据库驱动类异常处理
		       		System.out.println("数据库驱动加载失败!");  
		        	e.printStackTrace();  
		        } catch(SQLException e) {
		        	//数据库连接失败异常处理
		        	e.printStackTrace(); 
		        }catch (Exception e) {
		        	// TODO: handle exception
		        	e.printStackTrace();
		        }finally {
					System.out.println("数据库数据成功获取！");
				}
		        return checkclassstudentgrade;		
	}

}
