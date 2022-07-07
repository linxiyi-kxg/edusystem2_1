package serve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;

import model.ClassGradeCatalogFactory;
import model.ClassGradeCatalogFactoryMySQL;
import model.ClassInfoCatalogFactory;
import model.ClassInfoCatalogFactoryMySQL;
import model.ClassMemberCatalogFactory;
import model.ClassMemberCatalogFactoryMySQL;
import model.GradeInfoCatalogFactory;
import model.GradeInfoCatalogFactoryMySQL;
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
import model.TeacherClassCatalogFactory;
import model.TeacherClassCatalogFactoryMySQL;
import model.TeacherInfoCatalogFactory;
import model.TeacherInfoCatalogFactoryMySQL;

public class ServerThread extends Thread {
	Socket socket = null;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		//创建相应的参数
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		
		try {
			//读取配置文件
			String filepath = "serve/config.properties";
			ReadProperties readProperties = new ReadProperties(filepath);
			
			// 初始化学生端所需对象
			StudentAttendanceCatalogFactory studentAttendanceCatalogFactory = new StudentAttendanceCatalogFactoryMySQL();
			StudentConsumptionCatalogFactory studentConsumptionCatalogFactory = new StudentConsumptionCatalogFactoryMySQL();
			StudentGradeCatalogFactory studentGradeCatalogFactory = new StudentGradeCatalogFactoryMySQL();
			StudentInfoCatalogFactory studentInfoCatalogFactory = new StudentInfoCatalogFactoryMySQL();
			StudentTrackCatalogFactory studentTrackCatalogFactory = new StudentTrackCatalogFactoryMySQL();
			
			//初始化教师端所需对象
			TeacherInfoCatalogFactory teacherInfoCatalogFactory = new TeacherInfoCatalogFactoryMySQL();
			TeacherClassCatalogFactory teacherClassCatalogFactory = new TeacherClassCatalogFactoryMySQL();
			ClassMemberCatalogFactory classMemberCatalogFactory = new ClassMemberCatalogFactoryMySQL();
			ClassGradeCatalogFactory classGradeCatalogFactory = new ClassGradeCatalogFactoryMySQL();
			
			//初始化教务端所需对象
			ClassInfoCatalogFactory classInfoCatalogFactory = new ClassInfoCatalogFactoryMySQL();
			GradeInfoCatalogFactory gradeInfoCatalogFactory = new GradeInfoCatalogFactoryMySQL();
			
			//获取输入流，并读取客户端信息
            is = socket.getInputStream();
            //获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            
            ObjectInputStream ois = new ObjectInputStream(is);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            
            //获取传输的对象
            String code = (String) ois.readObject(); //功能序号
            ArrayList<String> paraList = (ArrayList<String>) ois.readObject(); //参数列表
            
            //登陆验证
            //通过身份认证后,获得相应的Controller权限
            String controller1 = "ParentsController";
            String controller2 = "TeacherController";
            String controller3 = "AdministratorController";
            
            //根据不同的功能使用不同的方法
            String method = null; //方法名
            Object message = new Object(); //服务器给客户端的消息
            String methodNum = readProperties.readerProperties(code);
            String classname1 = readProperties.readerProperties(controller1);
            String classname2 = readProperties.readerProperties(controller2);
            String classname3 = readProperties.readerProperties(controller3);
            Class<?> controllerclass1 = Class.forName(classname1);
            Class<?> controllerclass2 = Class.forName(classname2);
            Class<?> controllerclass3 = Class.forName(classname3);
            Constructor<?> constructor1 = controllerclass1.getConstructor(StudentInfoCatalogFactory.class, StudentTrackCatalogFactory.class, StudentAttendanceCatalogFactory.class, StudentConsumptionCatalogFactory.class, StudentGradeCatalogFactory.class);
            Constructor<?> constructor2 = controllerclass2.getConstructor(TeacherInfoCatalogFactory.class, TeacherClassCatalogFactory.class, ClassMemberCatalogFactory.class, ClassGradeCatalogFactory.class);
            Constructor<?> constructor3 = controllerclass3.getConstructor(StudentInfoCatalogFactory.class, TeacherInfoCatalogFactory.class, ClassInfoCatalogFactory.class, GradeInfoCatalogFactory.class);
            Object object1 = constructor1.newInstance(studentInfoCatalogFactory, studentTrackCatalogFactory, studentAttendanceCatalogFactory, studentConsumptionCatalogFactory, studentGradeCatalogFactory);
            Object object2 = constructor2.newInstance(teacherInfoCatalogFactory, teacherClassCatalogFactory, classMemberCatalogFactory, classGradeCatalogFactory);
            Object object3 = constructor3.newInstance(studentInfoCatalogFactory, teacherInfoCatalogFactory, classInfoCatalogFactory, gradeInfoCatalogFactory);
            
            for (int i = 1; i <= Integer.parseInt(methodNum); i++) {
            	switch (i) {
            	case 1:
            		method = readProperties.readerProperties(code + "method" + i);
            		//不同端使用不同的方法执行
            		if (Integer.parseInt(code) <= 5) {
            			//学生端
            			Method me = controllerclass1.getDeclaredMethod(method, int.class);
                		me.invoke(object1, Integer.parseInt(paraList.get(0)));
            		}
            		if (Integer.parseInt(code) > 5 && Integer.parseInt(code) <= 13) {
            			//教师端
            			Method me = controllerclass2.getDeclaredMethod(method, int.class);
            			message = me.invoke(object2, Integer.parseInt(paraList.get(0)));
            			if (Integer.parseInt(code) > 5 && Integer.parseInt(code) <= 8) {
            				System.out.println(message);
                    		oos.writeObject(message);
            			}
            		}
            		if (Integer.parseInt(code) > 13 && Integer.parseInt(code) <= 19) {
            			//教务端
            			if (Integer.parseInt(code) == 14 || Integer.parseInt(code) == 15 || Integer.parseInt(code) == 18) {
            				Method me = controllerclass3.getDeclaredMethod(method, ArrayList.class);
                			message = me.invoke(object3, paraList);
                			System.out.println(message);
                    		oos.writeObject(message);
            			}
            			if (Integer.parseInt(code) == 16) {
            				Method me = controllerclass3.getDeclaredMethod(method, int.class);
                			message = me.invoke(object3, Integer.parseInt(paraList.get(0)));
                			System.out.println(message);
                    		oos.writeObject(message);
            			}
            			if (Integer.parseInt(code) == 17) {
            				Method me = controllerclass3.getDeclaredMethod(method, int.class, int.class, int.class);
                			message = me.invoke(object3, Integer.parseInt(paraList.get(0)), Integer.parseInt(paraList.get(1)), Integer.parseInt(paraList.get(2)));
                			System.out.println(message);
                    		oos.writeObject(message);
            			}
            			if (Integer.parseInt(code) == 19) {
            				Method me = controllerclass3.getDeclaredMethod(method, int.class, int.class);
                			message = me.invoke(object3, Integer.parseInt(paraList.get(0)), Integer.parseInt(paraList.get(1)));
                			System.out.println(message);
                    		oos.writeObject(message);
            			}
            		}
            		break;
            	
            	case 2:
            		method = readProperties.readerProperties(code + "method" + i);
            		if (Integer.parseInt(code) <= 5) {
            			//学生端
            			Method me = controllerclass1.getDeclaredMethod(method, int.class); 
                		message = me.invoke(object1, Integer.parseInt(paraList.get(1)));
                		System.out.println(message);
                		oos.writeObject(message);
            		}
            		if (Integer.parseInt(code) > 5 && Integer.parseInt(code) <= 13) {
            			//教师端
            			if (Integer.parseInt(code) > 5 && Integer.parseInt(code) <= 8) {
            				Method me = controllerclass2.getDeclaredMethod(method);
                			me.invoke(object2);
            			}
            			if (Integer.parseInt(code) == 9) {
            				paraList.remove(0);
            				Method me = controllerclass2.getDeclaredMethod(method, ArrayList.class);
                			message = me.invoke(object2, paraList);
                    		oos.writeObject(message);
            			} else {
            				Method me = controllerclass2.getDeclaredMethod(method, int.class);
                			me.invoke(object2, Integer.parseInt(code));
						}
            		}
            		if (Integer.parseInt(code) > 13 && Integer.parseInt(code) <= 19) {
            			//教务端
            			Method me = controllerclass3.getDeclaredMethod(method);
            			me.invoke(object3);
            		}
            		
            		break;
            	
            	case 3:
            		method = readProperties.readerProperties(code + "method" + i);
            		if (Integer.parseInt(code) <= 5) {
            			//学生端
            			Method me = controllerclass1.getDeclaredMethod(method); 
                		message = me.invoke(object1);
            		}
            		if (Integer.parseInt(code) > 5 && Integer.parseInt(code) <= 13) {
            			//教师端
            			if (Integer.parseInt(code) == 9) {
            				Method me = controllerclass2.getDeclaredMethod(method); 
                    		message = me.invoke(object2);
            			} 
            			if (Integer.parseInt(code) == 10) {
            				Method me = controllerclass2.getDeclaredMethod(method, int.class, int.class);
                			message = me.invoke(object2, Integer.parseInt(paraList.get(1)), Integer.parseInt(code));
                			System.out.print(message + "\n");
                			oos.writeObject(message);
            			} else {
            				Method me = controllerclass2.getDeclaredMethod(method, int.class, int.class);
                			message = me.invoke(object2, Integer.parseInt(paraList.get(1)), Integer.parseInt(paraList.get(2)));
                			System.out.print(message + "\n");
                			oos.writeObject(message);
            			}
            		}
            		break;
            	
            	case 4:
            		method = readProperties.readerProperties(code + "method" + i);
            		Method me = controllerclass2.getDeclaredMethod(method); 
            		message = me.invoke(object2);
            		break;
            	
            	default:
            		throw new IllegalArgumentException("Unexpected value: " + i);
            	}
            }
            
            
	} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} finally {
		try {
            if (pw != null) {
            	pw.close();
            }   
            if (os != null) {
                os.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (is != null) {
                is.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	}
}
