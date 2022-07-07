package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import serve.ReadProperties;

public class TeacherInfoCatalogMySQL extends TeacherInfoCatalog {
    TeacherInfoCatalogMySQL(int teacherId) {
		super(teacherId);
		// TODO 自动生成的构造函数存根
	}
		
	@Override
	public ArrayList<String> getTeacherInfo() {	
		//查询老师基本信息:名字，教师编号，头像，年龄，性别，任课科目，任课班级，学历，联系电话，邮箱
		ArrayList<String> teacherInfo = new ArrayList<String>();
				
		//声明Calendar类，获取当前年份，计算年龄
		Calendar calendar = Calendar.getInstance();
		Integer year = calendar.get(Calendar.YEAR);
				
		//连接数据库
		//声明Connection对象
		Connection conn = null;
		//驱动程序名
		String driver = "com.mysql.cj.jdbc.Driver";
		//读取配置文件
		String filepath = "model/JDBC.properties";
		ReadProperties readProperties = new ReadProperties(filepath);
		
		try {
			String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
			String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
			String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
			//加载数据库
		    Class.forName(driver);
		    //连接数据库
		    conn = DriverManager.getConnection(url, user, password);
		    if (!conn.isClosed()) {
		    	System.out.println("数据库连接成功!");
		    }
		    	
		        	
		        	//创建statement对象，执行SQL语句
		        	Statement statement = conn.createStatement();
		        	//要执行的SQL语句
		        	String sql = "SELECT * FROM 软工小组项目.教师表 WHERE 教师表.教师ID = " + this.teacherId;
		        	
		        	//ResultSet类，用来存放获取的结果集
		        	ResultSet rs = statement.executeQuery(sql);
		        	
		        	//老师基本信息:教师ID，名字，年龄，性别, 任课科目，任课班级，学历，联系电话，邮箱
		        	Integer idint = null;	//教师ID
		        	String name = null;		//名字
		        	String gender = null;	//性别
		        	String nation = null;	//名族
		        	String politics = null;	//政治面貌
		        	String education = null;	//学历
		        	String brith = null;	//出生日期
		        	Long phoneint = null;	//联系电话
		        	String email = null;	//邮箱
		        	Integer birthint = null; //用于计算的出生日期
		        	String age = null;	//年龄
		        	String subject = null;	//任课科目
		        	
		        	
		        	while (rs.next()) {
		        		idint = rs.getInt("教师ID");	//获取教师ID
		        		name = rs.getString("教师名");	//获取教师名
		        		gender = rs.getString("性别");	//获取性别
		        		nation = rs.getString("民族");	//获取民族
		        		politics = rs.getString("政治面貌");	//获取政治面貌
		        		education = rs.getString("学历");	//获取学历
		        		brith = rs.getString("出生日期");	//获取出生日期
		        		phoneint = rs.getLong("联系电话");	//获取联系电话
		        		email = rs.getString("邮箱");	//获取邮箱
		        		
		        		
		        		//先将数据类型进行转换
		        		String id = Integer.toString(idint);	//对教师ID进行转换
		        		String phone = Long.toString(phoneint); 	//对电话进行转换
		        		
		        		//计算年龄
		        		brith = brith.substring(0, 4); //截取字符串前四位（年份）用于计算年龄
		        		//将字符类型转换为整型计算年龄
		        		birthint = Integer.valueOf(brith).intValue();
		        		Integer ageint = year - birthint;    
						age = Integer.toString(ageint); 
		        		
		        		//写入数组
		        		//teacherInfo.add(id);
		        		teacherInfo.add(name);
		        		teacherInfo.add(age);
		        		teacherInfo.add(gender);
		        		teacherInfo.add(nation);
		        		teacherInfo.add(politics);
		        		teacherInfo.add(education);
		        		teacherInfo.add(phone);
		        		teacherInfo.add(email);
		        		
		        		
		        		
					}
		        	rs.close();
		        	
		        	//再申请一个用于查询任课科目
		        	String sqllan = "SELECT 学科名 FROM 软工小组项目.学科表 WHERE 学科ID IN (SELECT 学科ID FROM 软工小组项目.`学期-教师-学科-班级表` WHERE 教师ID =" + this.teacherId + " )";
		        	ResultSet result = statement.executeQuery(sqllan);
//		        	if(result == null) {
//		        		teacherInfo.add(new String("0"));
//		        	}
		        	while (result.next()) {
						subject = result.getString("学科名");	//获取任课科目
						teacherInfo.add(subject);
		        		
					}
		        	result.close();
		        	conn.close();
		        	
				} catch (ClassNotFoundException e) {
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
		        return teacherInfo;
	}

	@Override
	public ArrayList<String> addTeacherInfo(ArrayList<String> teacherInfo) {
		// TODO 自动生成的方法存根
		ArrayList<String> result = new ArrayList<String>();
		String filepath = "model/JDBC.properties";
		ReadProperties readProperties = new ReadProperties(filepath);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = readProperties.readerProperties("URL");   //通过配置文件读取地址
			String user = readProperties.readerProperties("USER");	//通过配置文件读取用户名
			String password = readProperties.readerProperties("PASSWORD"); //通过配置文件读取密码
		    Connection connection = DriverManager.getConnection(url, user, password);
		    String pre = "";
	        for (int i = 0; i < teacherInfo.size(); i++) {
	 
	            if (i != teacherInfo.size() - 1) {
	            	 pre += "?,";
	            } else {
	            	 pre += "?";
	            }
	        }
	 
	        String sql = "INSERT INTO 教师表 VALUES(" + pre + ")";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        for (int i = 1; i <= teacherInfo.size(); i++) {
	 
	            preparedStatement.setString(i, teacherInfo.get(i - 1));
	 
	        }
	        preparedStatement.executeUpdate();
		} catch (SQLException | IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.add("成功录入以下数据");
		for (String dataset : teacherInfo) {
			result.add(dataset);
		}
		
		return result;
	}

}
