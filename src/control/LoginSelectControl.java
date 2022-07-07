package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import serve.ClientThread;


@SuppressWarnings("all")
public class LoginSelectControl {

    @FXML
    private MenuItem btnManager;
    @FXML
    private MenuItem btnStudent;
    @FXML
    private MenuItem btnTeacher;
    @FXML
    private Button login;
    @FXML
    private Label idText;
    @FXML
    private MenuButton identify;
    @FXML
    private TextField accountInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label accountMiss;

    @FXML
    private Button homeBtn;
    
  //教师基本信息
    @FXML
    private Label teacherAge;
    @FXML
    private Label teacherName;
    @FXML
    private Label teacherEducation;
    @FXML
    private Label teacherPhone;
    @FXML
    private Label teacherPolitics;
    @FXML
    private Label teacherGender;
    @FXML
    private Label teacherNation;
    @FXML
    private Label projectTeaching;
    
    //学生基本信息
    @FXML
    private Label studentHeadTeacher;
    @FXML
    private Label studentPolitics;
    @FXML
    private Label studentEnrollmentY;
    @FXML
    private Label enrollmentRanking;
    @FXML
    private Label studentId;
    @FXML
    private Label studentAge;
    @FXML
    private Label studentGender;
    @FXML
    private Label studentName;
    
    //连接服务端
    //特定功能需要的参数数组
    ArrayList<String> listInput = new ArrayList<String>();
    //传入的用户ID和密码
    ArrayList<String> loginData = new ArrayList<String>();

    //服务端传回来的可能是一维数组也可能是二维数组
    private String[] data1 = new String[0];
    private String[][] data2 = new String[0][0];
    
    private int btnCode; //登陆按钮是0号
    private boolean checkResult; //true OR false，但是传回来的时候是字符串
    private String loginResultPromptTxt; //label的文本，提示是否验证成功
    private String idSign;	//用于不同输入转换路径
    private int idNumber;	//用于验证不同身份的账户是否存在
    
	
	@FXML
	public void loginButtonAction() {
		loginData.clear();
		listInput.clear();
		
		loginData.add(accountInput.getText());
		loginData.add(passwordInput.getText());
//		btnCode = 0;
		//唯一的用处，但是新的登录接口还没实现
//		client(btnCode, listInput, loginData);
		
		loginCheck(loginData);
		
		loginResultCheck(); //检查是否登录失败，失败了会有提示
		selectIdentify(); //匹配身份，匹配idSign
    	
		loginButtonAction1(); //跳转以及页面初始化
	}
	
	
	@FXML
	public void loginButtonAction1() {
		if (checkResult) {
			
			//登陆界面传参：把ID和密码一起传过去控制类，这个时候还都是String的形式
			if (idSign.equals("teacher")) {
				TeacherController.setText(accountInput.getText(), passwordInput.getText());
			} else if (idSign.equals("student")) {
				StudentController.setText(accountInput.getText(), passwordInput.getText());
			} else if (idSign.equals("manager")) {
				ManagerController.setText(accountInput.getText(), passwordInput.getText());
			}
			
			Stage selectStage = (Stage) login.getScene().getWindow();
			selectStage.close();
			
			try {				
				Pane managerPane = FXMLLoader.load(getClass().getResource("/view/" + idSign + "Home.fxml"));
				Scene managerScene = new Scene(managerPane);	
				Stage managerStage = new Stage();	
				managerStage.setScene(managerScene);
				managerStage.setTitle("线上教育管理系统");
				
				managerStage.show();
//				managerStage.setResizable(false);
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//登陆失败应该有提示
	@FXML
	public void loginResultCheck() {

		if (!checkResult) {
			accountMiss.setText(loginResultPromptTxt);

		}
	}
	
	//确定用户身份，根据idNumber选择idSign
	@FXML
	public void selectIdentify() {
		switch (idNumber) {
		case 0:
			idSign = "student";
			break;
		case 1:
			idSign = "teacher";
			break;
		case 2:
			idSign = "manager";
			break;
		default:
		}
	}
		
	
	//验证输入，输入的第一个参数是账号，第二个参数是密码。正确密码都是1234，如果账号是1234就进入教务端，如果是2019611111就进入教师端，如果是123456就进入家长端，并进行参数的初始化	
	public void loginCheck(ArrayList<String> list) {
		if(list.get(1).equals("1234")) {
			if(list.get(0).equals("1234")) {
				checkResult = true;
				loginResultPromptTxt = "输入正确";
				idNumber = 2;
		
			}else if(list.get(0).equals("2019611111")) {
				checkResult = true;
				loginResultPromptTxt = "输入正确";
				idNumber = 1;
		
			}else if(list.get(0).equals("123456")) {
				checkResult = true;
				loginResultPromptTxt = "输入正确";
				idNumber = 0;
		
			}
		}
		else {
			checkResult = false;
        	loginResultPromptTxt = "输入账号或密码有误，请重新输入";
        	idNumber = -1;
		}
	}
	
//	public void client(int code, ArrayList<String> list, ArrayList<String> login) {
//		//参数注释：code是按钮序号，list是传入的参数，login是用户ID和密码
//		try {
//            //1.创建客户端Socket，指定服务器地址和端口
//			//如果不是本机链接，则将localhost改为链接服务器的IP
//            Socket socket = new Socket("10.22.27.42", 8888);
////			Socket socket=new Socket("172.16.99.168", 8888);
//            //2.获取输出流，向服务器端发送信息
//            OutputStream os = socket.getOutputStream(); //字节输出流
//            InputStream is = socket.getInputStream(); //字节输入流
//
//            //创建输出对象流，将对象输出
//            ObjectOutputStream oos = new ObjectOutputStream(os);
//            ObjectInputStream ois = new ObjectInputStream(is);
//            //oos.writeObject(test);
//            //根据输入的序号执行对应的功能
//            //本功能目前没有实现传输数据，只是把得到的数据进行打印
//            switch (code) {
//            case 0: {
//            	oos.writeObject(code);
//            	oos.writeObject(list);
//            	oos.writeObject(login);
//            	ArrayList<Object> object =  (ArrayList<Object>) ois.readObject();
//            	System.out.println(object);
//            	object.add("-1");
//            	checkResult = object.get(0).toString().equals("true");
//            	loginResultPromptTxt = object.get(1).toString();
//            	idNumber = Integer.parseInt(object.get(2).toString());
//            	
//            	//object的第一个元素就是我的登陆是否会成功，第二个信息是一段文本，第三个信息是账户类型
//            	
//            	
//            	break;
//            }
//            case 1: {
//	            	
//	            	//查看学生个人信息
//	            	oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object); 
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//					break;
//				}
//	            case 2: {
//	            	//查看成长档案
//	            	oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<Integer> object = (ArrayList<Integer>) ois.readObject();
//					System.out.println(object); 
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//				}
//	            case 3: {
//	            	//查看考勤记录
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<Integer> object = (ArrayList<Integer>) ois.readObject();
//					System.out.println(object); 
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//					break;
//				}
//				case 4: {
//					//查看消费记录
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<Double> object = (ArrayList<Double>) ois.readObject();
//					System.out.println(object); 
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//					break;
//				}
//				case 5: {
//					//查看学业画像
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<Integer>> object = (ArrayList<ArrayList<Integer>>) ois.readObject();
//					System.out.println(object); 
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//	            	break;
//				}
//				case 6: {
//					//查看班级学生信息
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object); 
//					//输入：学生ID
//					//返回：学生姓名、年龄、性别、学号、入学年份、政治面貌，入学排名，查看班主任老师
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//				}
//				case 7: {
//	            	
//	            	//查看学生考勤记录
//					oos.writeObject(code);
//					oos.writeObject(list);
//					
//					ArrayList<Integer> object = (ArrayList<Integer>) ois.readObject();
//					System.out.println(object);  
//					//输入：学生ID、最近n次时间（最近1个月、3个月、5个月）
//					//返回：最近每个月缺勤次数(一维数组)
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//	            }
//	            case 8: {
//	            	
//	            	//查看学生成长档案
//	            	oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<Integer> object = (ArrayList<Integer>) ois.readObject();
//					System.out.println(object);
//					//输入：学生ID（？）、最近n次时间（最近3次、5次、10次）
//					//返回：最近n次考试的总分(一维数组)
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//	            }
//	            case 9: {
//	            	
//	            	//查看学生的学业画像
//	            	oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<Integer>> object = (ArrayList<ArrayList<Integer>>) ois.readObject();
//					System.out.println(object);
//					//输入：学生id(?),最近n次考试(最近3次、5次、10次)
////					返回：二维数组，最近n次考试每科的总分/排名
////					行是科目，列是次数
////					[[语文1，语文2，…,语文n]
////					[数学1，数学2，…,数学n]
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//	            	break;
//	            }
//				case 10: {
//					//查看班级学业信息
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<Integer>> object = (ArrayList<ArrayList<Integer>>) ois.readObject();
//					System.out.println(object); 
////					输入：老师ID，学期ID、最近n次考试、考试科目ID
////					返回：二维数组
////					[[第1次考试平均分，第2次考试平均分，…,第n次考试平均分]，
////					[第1次考试最高分，第2次考试最高分，…,第n次考试最高分]，
////					[第1次考试最低分，第2次考试最低分，…,第n次考试最低分]
////					]
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//					break;
//				}
//				case 11: {
//	            	//查询老师基本信息
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object); 
////					输入：老师ID
////					返回：名字，年龄，性别，民族，政治面貌，学历，联系电话（参考教师表），邮箱，任课科目。
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//					break;
//				}
//	            case 12: {
//	            	//查看任课班级
//	            	oos.writeObject(code);
//	            	oos.writeObject(list);
//	            	oos.writeObject(login);
//	            	ArrayList<String> object = (ArrayList<String>) ois.readObject();
//	            	System.out.println(object);
////	            	输入：老师ID
////	            	返回：一维数组，老师任课的班级[4,7,8]
//	            	data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//					break;
//				}
//	            case 13: {
//	            	//查看班级学生名单
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object); 
////					输入：老师ID
////					返回：一维数组（班级的每个学生的名字）
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//					break;
//				}
//	            //下面的都是教务处的
//	            case 14: {
//	            	
//	            	//查看学生的基本信息
//	            	oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object);
////					输入：学生ID
////					返回：学生姓名、年龄、性别、学号、入学年份、政治面貌，入学排名，查看班主任老师
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//	            }
//				case 15: {
//	            	
//	            	//查看学生考勤记录
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<Integer> object = (ArrayList<Integer>) ois.readObject();
//					System.out.println(object); 
////					输入：学生ID（？）、最近n次时间（最近1个月、3个月、5个月）
////					返回：最近每个月缺勤次数(一维数组)
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//	            }
//				case 16: {
//	            	
//	            	//查看学生成长档案
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<Integer> object = (ArrayList<Integer>) ois.readObject();
//					System.out.println(object);
////					输入：学生ID（？）、最近n次时间（最近3次、5次、10次）
////					返回：最近n次考试的总分(一维数组)
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//	            }
//				case 17: {
//	            	
//	            	//查看学生的学业画像
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<Integer>> object = (ArrayList<ArrayList<Integer>>) ois.readObject();
//					System.out.println(object);
////					输入：学生id（？）、最近n次考试(最近3次、5次、10次)
////					返回：二维数组，最近n次考试每科的总分/排名
////							行是科目，列是次数
////							[[语文1，语文2，…,语文n]
////							[数学1，数学2，…,数学n]
////							…
////							]
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//	            	break;
//	            }
//				case 18: {
//	            	
//	            	//查看老师基本信息
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object);
////					输入：老师ID
////					返回：名字，年龄，性别，任课科目，学历，联系电话（参考教师表）
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//	            }
//				case 19: {
//	            	
//	            	//查看班级学生信息表
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<Integer>> object = (ArrayList<ArrayList<Integer>>) ois.readObject();
//					System.out.println(object);
////					输入：班级ID
////					返回：一维数组（班级的每个学生的名字）
//					data1 = new String[0];
//					for (int i = 0; i < object.size(); i++) {
//						addToData1(object.get(i).toString());
//					}
//	            	break;
//	            }
//				case 20: {
//	            	
//	            	//录入学生信息
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object);
//					//输入：ID、姓名、性别、民族、入学年份、出生日期、家庭住址、家庭类型、政治面貌、入学排名、宿舍号、是否退学
//	            	break;
//	            }
//				case 21: {
//	            	
//	            	//录入教师信息
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<String> object = (ArrayList<String>) ois.readObject();
//					System.out.println(object);
////					输入（表头）：教师ID、姓名、性别、民族、政治面貌、学历、出生日期、联系电话、邮箱
//	            	break;
//	            }
//				case 22: {
//	            	
//	            	//按照考勤类型查看考勤情况
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<String>> object = (ArrayList<ArrayList<String>>) ois.readObject();
//					System.out.println(object);
//					//输入时间段
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//	            	break;
//	            }
//				case 23: {
//	            	
//	            	//按照班级查看考勤情况
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<String>> object = (ArrayList<ArrayList<String>>) ois.readObject();
//					System.out.println(object);
//					//输入时间段
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//					
//	            	break;
//	            }
//				case 24: {
//	            	
//	            	//统计年级学业情况
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<String>> object = (ArrayList<ArrayList<String>>) ois.readObject();
//					System.out.println(object);
////					输入：学科ID、最近n次时间（最近3次、5次、10次）
////					返回：二维数组（针对全年级的学生、查询时间需要很久）
////					[[第1次考试平均分，第2次考试平均分，…,第n次考试平均分]，
////					[第1次考试最高分，第2次考试最高分，…,第n次考试最高分]，
////					[第1次考试最低分，第2次考试最低分，…,第n次考试最低分]
////					]
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//	            	break;
//	            }
//				case 25: {
//	            	
//	            	//统计班级学业
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<String>> object = (ArrayList<ArrayList<String>>) ois.readObject();
//					System.out.println(object);
////					输入：班级ID、学科ID、最近n次时间（最近3次、5次、10次）
////					返回：二维数组（针对全年级的学生、查询时间需要很久）
////					[[第1次考试平均分，第2次考试平均分，…,第n次考试平均分]，
////					[第1次考试最高分，第2次考试最高分，…,第n次考试最高分]，
////					[第1次考试最低分，第2次考试最低分，…,第n次考试最低分]
////					]
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for(int j=0;j<object.get(i).size();j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//	            	break;
//	            }
//				case 26: {
//	            	
//	            	//查看年级班级学业情况
//					oos.writeObject(code);
//					oos.writeObject(list);
//					oos.writeObject(login);
//					ArrayList<ArrayList<String>> object = (ArrayList<ArrayList<String>>) ois.readObject();
//					System.out.println(object);
//					//学科ID
//					data2 = new String[object.size()][object.get(0).size()];
//					for (int i = 0; i < object.size(); i++) {
//						for (int j = 0; j < object.get(i).size(); j++) {
//							data2[i][j] = object.get(i).get(j).toString();
//						}
//					}
//	            	break;
//	            }
//				default:
//					System.out.println("没有对应的序号：" + code);
//					
//				}
//          
//            socket.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} 
//	}
//	
	
	
	

}
