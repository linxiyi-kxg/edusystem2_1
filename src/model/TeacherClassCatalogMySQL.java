package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import serve.ReadProperties;

public class TeacherClassCatalogMySQL extends TeacherClassCatalog {
        
	public TeacherClassCatalogMySQL(int teacherId) {
		super(teacherId);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public ArrayList<String> getTeacherClass() {	
		//查看任课班级名单
		
		ArrayList<String> classlist = new ArrayList<String>();

		Connection conn = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		
		//读取配置文件
		String filepath = "model/JDBC.properties";
		ReadProperties readProperties = new ReadProperties(filepath);
		
		
		try {
			String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
			String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
			String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
			
			Class.forName(driver);
        	//连接数据库
        	conn = DriverManager.getConnection(url, user, password);
        	if(!conn.isClosed())
        		System.out.println("数据库连接成功!");
        	Statement statement = conn.createStatement();
        	
        	String sql = "SELECT 班级名 FROM 班级表 where 班级ID in (select 班级ID from `学期-教师-学科-班级表` where 教师ID=" + this.teacherId + ")";
        	ResultSet rs = statement.executeQuery(sql);
        	
        	Integer id = null;
        	String name = null;
        	
        	while(rs.next()) {
        		name = rs.getString("班级名");
        		classlist.add(name);
        	}
        	rs.close();
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
		
		return classlist;
	}

}
