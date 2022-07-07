package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ClassStudent;
import model.LoginId;
import model.StudentList;
import model.TeacherList;
import serve.ClientThread;

@SuppressWarnings("all")
public class ManagerController implements Initializable {

	//登录界面传递id的变量
	public static LoginId id = new LoginId();
	
	@FXML
    private Button studentInfoBtn;
	@FXML
    private Button classStudentBtn;
    @FXML
    private Label headTxt;
    @FXML
    private Button teacherInfoBtn;
    @FXML
    private Button classAttendanceBtn;
    @FXML
    private Button statisticsGradeScoreBtn;
    @FXML
    private Button statisticeClassScoreBtn;
    @FXML
    private Button logOut;
    
    //记录所有被选择的文本
    private String itemSelect;
    
    //学生成长档案
    @FXML
    private LineChart<String, Number> studentGrowingGrade;
    @FXML
    private MenuItem studentAllGradeLastTenTimesBtn;
    @FXML
    private MenuItem studentAllGradeLastFiveTimesBtn;
    @FXML
    private MenuItem studentAllGradeLastThreeTimesBtn;
    @FXML
    private MenuButton studentGrowingTimesConfirm;
    @FXML
    private TextField studentGrowingIdInput;
    @FXML
    private Label emptyWarning1;

    //学生考勤记录
    @FXML
    private TextField studentAttendanceIdInput;
    @FXML
    private MenuItem studentAbsenceNearestFiveMonthBtn;
    @FXML
    private MenuItem studentAbsenceNearestThreeMonthBtn;
    @FXML
    private MenuItem studentAbsenceNearestOneMonthBtn;
    @FXML
    private MenuButton studentAttendanceMonthConfirm;
    @FXML
    private BarChart<String, Integer> studentAttendanceAbsence;
    @FXML
    private CategoryAxis studentAttendanceLineXType;
    @FXML
    private Label emptyWarning2;
    
    //学生学业画像
    @FXML
    private LineChart<String, Number> studentGradeOptionalSubjects;
    @FXML
    private LineChart<String, Number> studentGradeRequiredSubjects;
    @FXML
    private TextField studentScoreIdInput;
    @FXML
    private MenuButton studentScoreTimesConfirm;
    @FXML
    private MenuItem studentGradeNearestThreeTimes;
    @FXML
    private MenuItem studentGradeNearestFiveTimes;
    @FXML
    private MenuItem studentGradeNearestTenTimes;
    @FXML
    private Label emptyWarning4;
    
    //查看班级学生名单
    @FXML
    private TableColumn<ClassStudent, String> colStudentName;
    @FXML
    private TableView<ClassStudent> tvClassStudent;
    @FXML
    private TextField classStudentIdInput;
    @FXML
    private Label emptyWarning3;
    
    //查看总体最近考勤特有的变量
    private String startY = "0";
    private String startM = "0";
    private String startD = "0";
    private String endY = "0";
    private String endM = "0";
    private String endD = "0";
    
    @FXML
    private Label warning11;
    @FXML
    private PieChart absenceTypes; //饼图
    @FXML
    private BarChart<String, Number> absenceNumberClass; //条形图
    @FXML
    private CategoryAxis lineXAxisClassId;
    @FXML
    private NumberAxis lineYAxisNumber;
    @FXML
    private MenuButton classAttendanceStartY;
    @FXML
    private MenuButton classAttendanceStartM;
    @FXML
    private MenuButton classAttendanceStartD;
    @FXML
    private MenuButton classAttendanceEndY;
    @FXML
    private MenuButton classAttendanceEndM;
    @FXML
    private MenuButton classAttendanceEndD;
    
    
    //年级学业
    private String subjectNumber1 = "0";
    @FXML
    private Label emptyWarning5;
    @FXML
    private LineChart<String, Number> gradeScore;
    @FXML
    private MenuButton statisticsGradeScoreTimesConfirm;
    @FXML
    private MenuButton statisticsGradeScoreSubjectConfirm;
    @FXML
    private MenuItem statisticsGradeScoreChineseBtn;
    @FXML
    private MenuItem statisticsGradeScorePhysicsBtn;
    @FXML
    private MenuItem statisticsGradeScoreEnglishBtn;
    @FXML
    private MenuItem statisticsGradeScoreBiologyBtn;
    @FXML
    private MenuItem statisticsGradeScorePoliticsBtn;
    @FXML
    private MenuItem statisticsGradeScoreMathBtn;
    @FXML
    private MenuItem statisticsGradeScoreChemistryBtn;
    @FXML
    private MenuItem statisticsGradeScoreHistoryBtn;
    @FXML
    private MenuItem statisticsGradeScoreGeographyBtn;
    @FXML
    private MenuItem gradeLastThreeTimesBtn;
    @FXML
    private MenuItem gradeLastFiveTimesBtn;
    @FXML
    private MenuItem gradeLastTenTimesBtn;
    
    //班级学业
    private String subjectNumber2 = "0";
    @FXML
    private Label emptyWarning6;
    @FXML
    private TextField statisticsClassScoreIdInput;
    @FXML
    private MenuButton statisticsClassScoreSubjectConfirm;
    @FXML
    private MenuButton statisticsClassScoreTimesConfirm;
    @FXML
    private MenuItem classLastThreeTimesBtn;
    @FXML
    private MenuItem classLastFiveTimesBtn;
    @FXML
    private MenuItem classLastTenTimesBtn;
    @FXML
    private LineChart<String, Number> classScore;
    

    //学生基本信息
    @FXML
    private Label emptyWarning8;
    @FXML
    private TextField studentInfoIdInput;
    @FXML
    private TableView<StudentList> tvStudentList;
    @FXML
    private TableColumn<StudentList, String> studentInfoColAge;
    @FXML
    private TableColumn<StudentList, String> studentInfoColEnrollmentY;
    @FXML
    private TableColumn<StudentList, String> studentInfoColPolitics;
    @FXML
    private TableColumn<StudentList, String> studentInfoColHeadTeacher;
    @FXML
    private TableColumn<StudentList, String> studentInfoColName;
    @FXML
    private TableColumn<StudentList, String> studentInfoColGender;
    @FXML
    private TableColumn<StudentList, String> studentInfoColId;
    @FXML
    private TextField studentInfoTfId;
    @FXML
    private TextField studentInfoTfPolitics;
    @FXML
    private TextField studentInfoTfRanking;
    @FXML
    private TextField studentInfoTfDormitory;
    @FXML
    private TextField studentInfoTfFamilyT;
    @FXML
    private TextField studentInfoTfDropOut;
    @FXML
    private TextField studentInfoTfNation;
    @FXML
    private TextField studentInfoTfGender;
    @FXML
    private TextField studentInfoTfBirth;
    @FXML
    private TextField studentInfoTfEnrollmentY;
    @FXML
    private TextField studentInfoTfName;
    @FXML
    private TextField studentInfoTfHomeA;


    
    //教师基本信息
    @FXML
    private Label emptyWarning9;
    @FXML
    private Label emptyWarning10;
    @FXML
    private TableView<TeacherList> tvTeacherList;
    @FXML
    private TableColumn<TeacherList, String> teacherInfoColName;
    @FXML
    private TableColumn<TeacherList, String> teacherInfoColGender;
    @FXML
    private TableColumn<TeacherList, String> teacherInfoColNation;
    @FXML
    private TableColumn<TeacherList, String> teacherInfoColEducation;
    @FXML
    private TableColumn<TeacherList, String> teacherInfoColPhone;
    @FXML
    private TableColumn<TeacherList, String> teacherInfoColAge;
    @FXML
    private TableColumn<TeacherList, String> teacherInfoColPolitics;
    @FXML
    private TextField teacherInfoTfName;
    @FXML
    private TextField teacherInfoTfPolitics;
    @FXML
    private TextField teacherInfoTfBirth;
    @FXML
    private TextField teacherInfoTfId;
    @FXML
    private TextField teacherInfoTfEducation;
    @FXML
    private TextField teacherInfoTfNation;
    @FXML
    private TextField teacherInfoTfPhone;
    @FXML
    private TextField teacherInfoTfEmail;
    @FXML
    private TextField teacherInfoTfGender;
    @FXML
    private TextField teacherInfoIdInput;
    
    //连接服务端
    //特定功能需要的参数数组
    ArrayList<String> listInput = new ArrayList<String>();
    //传入的用户ID和密码
    ArrayList<String> loginData = new ArrayList<String>();
    
    private String btnCode; //输入client的按钮序号
    
    //服务端传回来的可能是一维数组也可能是二维数组
    private String[] data1 = new String[0];
    private String[][] data2 = new String[0][0];
    
    
  //返回首页
    @FXML
    void homeBtnClick(ActionEvent event) {
    	System.out.println("返回首页");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		try {	
			Pane homePane = FXMLLoader.load(getClass().getResource("/view/managerHome.fxml"));
			
			Scene homeScene = new Scene(homePane);
			selectStage.setScene(homeScene);
			selectStage.setTitle("教务端");
			selectStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void logOutClick(ActionEvent event) {
    	System.out.println("退出登录");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		selectStage.close();
		
		try {	
			Pane infoPane = FXMLLoader.load(getClass().getResource("/view/Login_Select.fxml"));
			Scene infoScene = new Scene(infoPane);
			Stage managerStage = new Stage();
			managerStage.setScene(infoScene);
			managerStage.setTitle("数智教育");
			managerStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //查看学生信息列表
    @FXML
    void studentInfoBtnClick(ActionEvent event) {
    	System.out.println("学生个人基本信息");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		
		try {	
			Pane infoPane = FXMLLoader.load(getClass().getResource("/view/managerStudentInfo.fxml"));
			
			Scene infoScene = new Scene(infoPane);
			selectStage.setScene(infoScene);
			selectStage.setTitle("教务端");
			selectStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    //查看教师信息列表
    @FXML
    void teacherInfoBtnClick(ActionEvent event) {
    	System.out.println("教师基本信息");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		
		try {	
			Pane infoPane = FXMLLoader.load(getClass().getResource("/view/managerTeacherInfo.fxml"));
			
			Scene infoScene = new Scene(infoPane);
			selectStage.setScene(infoScene);
			selectStage.setTitle("教务端");
			selectStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    //班级学生名单
    @FXML
    void classStudentBtnClick(ActionEvent event) {
    	System.out.println("班级学生名单");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		
		try {	
			Pane infoPane = FXMLLoader.load(getClass().getResource("/view/managerClassStudent.fxml"));
			
			Scene infoScene = new Scene(infoPane);
			selectStage.setScene(infoScene);
			selectStage.setTitle("教务端");
			selectStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void classAttendanceBtnClick(ActionEvent event) {
    	System.out.println("学生总考勤情况");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		
		try {	
			Pane infoPane = FXMLLoader.load(getClass().getResource("/view/managerClassAttendance.fxml"));
			Scene infoScene = new Scene(infoPane);
			selectStage.setScene(infoScene);
			selectStage.setTitle("教务端");
			selectStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void statisticsGradeScoreBtnClick(ActionEvent event) {
    	System.out.println("年级学业统计");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		
		try {	
			Pane infoPane = FXMLLoader.load(getClass().getResource("/view/managerStatisticsGradeScore.fxml"));
			Scene infoScene = new Scene(infoPane);
			selectStage.setScene(infoScene);
			selectStage.setTitle("教务端");
			selectStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void statisticsClassScoreBtnClick(ActionEvent event) {
    	System.out.println("班级学业统计");
    	
		Stage selectStage = (Stage) headTxt.getScene().getWindow();
		
		try {	
			Pane infoPane = FXMLLoader.load(getClass().getResource("/view/managerStatisticsClassScore.fxml"));
			Scene infoScene = new Scene(infoPane);
			selectStage.setScene(infoScene);
			selectStage.setTitle("教务端");
			selectStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    //学生总体考勤开始时间终止时间预记录
    @FXML
    void classAttendanceStartYClick(ActionEvent event) {

	    classAttendanceStartY.setText(((MenuItem) event.getTarget()).getText());
    }
    @FXML
    void classAttendanceStartMClick(ActionEvent event) {
    	
    	classAttendanceStartM.setText(((MenuItem) event.getTarget()).getText());
    }
    @FXML
    void classAttendanceStartDClick(ActionEvent event) {

    	classAttendanceStartD.setText(((MenuItem) event.getTarget()).getText());
    }
    @FXML
    void classAttendanceEndYClick(ActionEvent event) {

    	classAttendanceEndY.setText(((MenuItem) event.getTarget()).getText());
    }
    @FXML
    void classAttendanceEndMClick(ActionEvent event) {

    	classAttendanceEndM.setText(((MenuItem) event.getTarget()).getText());
    }
    @FXML
    void classAttendanceEndDClick(ActionEvent event) {

    	classAttendanceEndD.setText(((MenuItem) event.getTarget()).getText());
    }
    
    //学生考勤————每种类型、每个班
    @FXML
    void overallAttendanceClick(ActionEvent event) {

		System.out.println("班级最近考勤情况——饼图和条形图");
	    //把时间段组合出来，验证时间段是否符合格式要求
	    startY = classAttendanceStartY.getText();
	    startM = classAttendanceStartM.getText();
	    startD = classAttendanceStartD.getText();
	    endY = classAttendanceEndY.getText();
	    endM = classAttendanceEndM.getText();
	    endD = classAttendanceEndD.getText();
	    if (Integer.parseInt(startY) > Integer.parseInt(endY)) { //起始年份比终止年份大
	    	warning11.setText("时间段输入格式错误，请重新选择时间段！");
	    } else if ((startY.equals(endY)) && (Integer.parseInt(startM) > Integer.parseInt(endM))) {
	    	warning11.setText("时间段输入格式错误，请重新选择时间段！");
	    } else if ((startY.equals(endY)) && (startM.equals(endM)) && (Integer.parseInt(startD) > Integer.parseInt(endD))) {
	    	warning11.setText("时间段输入格式错误，请重新选择时间段！");
	    } else {
	    	clearClientParameter();
			loginData.add(id.getId());
			loginData.add(id.getPassword());
			listInput.add(startY + "-" + startM + "-" + startD);
			listInput.add(endY + "-" + endM + "-" + endD);
			
			btnCode = "18";
			client(btnCode, listInput);
			overallAttendancePieChart();
			
//			btnCode = 23;
//			client(btnCode,listInput,loginData);
			overallAttendanceBarChart();
	    }
		
    }
    
    
	//学生考勤————每种类型缺勤多少人————饼图
	public void overallAttendancePieChart() {
		System.out.println("班级最近十次考勤情况——饼图");
		ObservableList<Data> answer = FXCollections.observableArrayList();
		
		int x = Integer.parseInt(data2[0][0]);
		for (int i = 0; i < data2.length - 1 - x; i++) {
			answer.addAll(new PieChart.Data(data2[i + x + 1][0], Double.parseDouble(data2[i + x + 1][1])));
		}
		
        absenceTypes.setData(answer);
	}
	
	//学生考勤————每个班缺勤多少人————条形图
		public void overallAttendanceBarChart() {
			System.out.println("班级最近十次考勤情况——条形图");
			lineXAxisClassId.setTickLabelRotation(90);
			
			XYChart.Series<String, Number> series = new XYChart.Series<>(); 
			series.setName("各班缺勤人数"); 
			
			int x = Integer.parseInt(data2[0][1]);
			for (int i = 1; i < x; i++) {
				series.getData().add(new XYChart.Data<>(data2[i][0], Double.parseDouble(data2[i][1])));
			}
			absenceNumberClass.getData().add(series);
			
		}
		
		
		//年级学业画像（班级学业画像）————选择学科
		@FXML
	    void statisticsGradeScoreSubjectClick(ActionEvent event) {
			statisticsGradeScoreSubjectConfirm.setText(((MenuItem) event.getTarget()).getText());
			MenuItem item = (MenuItem) event.getTarget();
			itemSelect = item.getText();
  			if (itemSelect.equals("语文")) {
  				subjectNumber1 = "1";
  				
  			} else if (itemSelect.equals("数学")) {
  				subjectNumber1 = "2";
  				
  			} else if (itemSelect.equals("英语")) {
  				subjectNumber1 = "3";
  				
  			} else if (itemSelect.equals("物理")) {
  				subjectNumber1 = "4";
  				
  			} else if (itemSelect.equals("化学")) {
  				subjectNumber1 = "5";
  				
  			} else if (itemSelect.equals("生物")) {
  				subjectNumber1 = "6";
  				
  			} else if (itemSelect.equals("历史")) {
  				subjectNumber1 = "7";
  				
  			} else if (itemSelect.equals("地理")) {
  				subjectNumber1 = "8";
  				
  			} else {
  				subjectNumber1 = "17";
  			}
	    }
		
		//年级学业画像（班级学业画像）————选择学科
		@FXML
	    void statisticsClassScoreSubjectClick(ActionEvent event) {
			statisticsClassScoreSubjectConfirm.setText(((MenuItem) event.getTarget()).getText());
			MenuItem item = (MenuItem) event.getTarget();
			itemSelect = item.getText();
  			if (itemSelect.equals("语文")) {
  				subjectNumber2 = "1";
  				
  			} else if (itemSelect.equals("数学")) {
  				subjectNumber2 = "2";
  				
  			} else if (itemSelect.equals("英语")) {
  				subjectNumber2 = "3";
  				
  			} else if (itemSelect.equals("物理")) {
  				subjectNumber2 = "4";
  				
  			} else if (itemSelect.equals("化学")) {
  				subjectNumber2 = "5";
  				
  			} else if (itemSelect.equals("生物")) {
  				subjectNumber2 = "6";
  				
  			} else if (itemSelect.equals("历史")) {
  				subjectNumber2 = "7";
  				
  			} else if (itemSelect.equals("地理")) {
  				subjectNumber2 = "8";
  				
  			} else {
  				subjectNumber2 = "17";
  			}
	    }		
		
		//年级学业画像（每一科）————折线图
		@FXML
	    void gradeScoreClick(ActionEvent event) {
			System.out.println("学生学业画像——多道折线图");
				statisticsGradeScoreTimesConfirm.setText(((MenuItem) event.getTarget()).getText());

			if (!subjectNumber1.equals("0")) {
				emptyWarning5.setText("");
				clearClientParameter();
//				loginData.add(id.getId());
//				loginData.add(id.getPassword());
				listInput.add(subjectNumber1);
				btnCode = "19";
				
				MenuItem item = (MenuItem) event.getTarget();
				itemSelect = item.getText();
	  			if (itemSelect.equals("最近3次")) {
	  				listInput.add("3");
	  				gradeLastThreeTimesBtn.setDisable(true);
	  				gradeLastFiveTimesBtn.setDisable(false);
	  				gradeLastTenTimesBtn.setDisable(false);
	  				
	  			} else if (itemSelect.equals("最近5次")) {
	  			 	listInput.add("5");
	  				gradeLastFiveTimesBtn.setDisable(true);
	  				gradeLastThreeTimesBtn.setDisable(false);
	  				gradeLastTenTimesBtn.setDisable(false);
	  		
	  			} else if (itemSelect.equals("最近10次")) {
	  				listInput.add("10");
	  				gradeLastTenTimesBtn.setDisable(true);
	  				gradeLastThreeTimesBtn.setDisable(false);
	  				gradeLastFiveTimesBtn.setDisable(false);
	  			}
	  			client(btnCode, listInput);
	  			
	  			ObservableList<XYChart.Series<String, Number>> series1 = FXCollections.observableArrayList();
				
				Series<String, Number> highest = new Series<String, Number>();
				Series<String, Number> average = new Series<String, Number>();
				Series<String, Number> lowest = new Series<String, Number>();
				highest.setName("最高分");
				average.setName("平均分");
				lowest.setName("最低分");
		        
				for (int i = 0; i < data2.length; i++) {
					highest.getData().add(new javafx.scene.chart.XYChart.Data<String, Number>(Integer.toString(i + 1), Integer.parseInt(data2[i][1])));
					average.getData().add(new javafx.scene.chart.XYChart.Data<String, Number>(Integer.toString(i + 1), Integer.parseInt(data2[i][0])));
					lowest.getData().add(new javafx.scene.chart.XYChart.Data<String, Number>(Integer.toString(i + 1), Integer.parseInt(data2[i][2])));
					
				}
				series1.addAll(highest, average, lowest);
				gradeScore.setData(series1);
	  			
			} else {
				emptyWarning5.setText("请先选择要查询的学科再对时间段进行选择！");
			}
			
			
		}
			
		//班级学业画像（每一科）————折线图
		@FXML
	    void classScoreClick(ActionEvent event) {
			System.out.println("班级学业画像——多道折线图");
				statisticsClassScoreTimesConfirm.setText(((MenuItem) event.getTarget()).getText());
			
			if (statisticsClassScoreIdInput.getText().equals("")) {
				emptyWarning6.setText("请先输入班级ID！");
			} else if (subjectNumber2.equals("0")) {
				emptyWarning6.setText("请先选择需要查询的学科！");
			} else {
				emptyWarning6.setText("");
				clearClientParameter();
//				loginData.add(id.getId());
//				loginData.add(id.getPassword());
				listInput.add(statisticsClassScoreIdInput.getText());
				listInput.add(subjectNumber2);
				btnCode = "17";
				
				MenuItem item = (MenuItem) event.getTarget();
				itemSelect = item.getText();
	  			if (itemSelect.equals("最近3次")) {
	  				listInput.add("3");
	  				classLastThreeTimesBtn.setDisable(true);
	  				classLastFiveTimesBtn.setDisable(false);
	  				classLastTenTimesBtn.setDisable(false);
	  				
	  			} else if (itemSelect.equals("最近5次")) {
	  				listInput.add("5");
	  				classLastFiveTimesBtn.setDisable(true);
	  				classLastThreeTimesBtn.setDisable(false);
	  				classLastTenTimesBtn.setDisable(false);
	  		
	  			} else if (itemSelect.equals("最近10次")) {
	  				listInput.add("10");
	  				classLastTenTimesBtn.setDisable(true);
	  				classLastThreeTimesBtn.setDisable(false);
	  				classLastFiveTimesBtn.setDisable(false);
	  			}
	  			client(btnCode, listInput);
	  			
	  			ObservableList<XYChart.Series<String, Number>> series1 = FXCollections.observableArrayList();
				
				Series<String, Number> highest = new Series<String, Number>();
				Series<String, Number> average = new Series<String, Number>();
				Series<String, Number> lowest = new Series<String, Number>();
				highest.setName("最高分");
				average.setName("平均分");
				lowest.setName("最低分");
		        
				for (int i = 0; i < data2.length; i++) {
					highest.getData().add(new javafx.scene.chart.XYChart.Data<String, Number>(Integer.toString(i + 1), Integer.parseInt(data2[i][1])));
					average.getData().add(new javafx.scene.chart.XYChart.Data<String, Number>(Integer.toString(i + 1), Integer.parseInt(data2[i][0])));
					lowest.getData().add(new javafx.scene.chart.XYChart.Data<String, Number>(Integer.toString(i + 1), Integer.parseInt(data2[i][2])));
					
				}
				series1.addAll(highest, average, lowest);
				classScore.setData(series1);
			}
		}		
		
		//输出班级学生名单
		@FXML
	    void showClassStudent(ActionEvent event) {
  	    	
			if (!classStudentIdInput.getText().equals("")) {
				emptyWarning3.setText("");
				clearClientParameter();
	  			
//	  			loginData.add(id.getId());
//	  			loginData.add(id.getPassword());
	  			listInput.add(classStudentIdInput.getText());
	  			btnCode = "16";
	  			
	  			client(btnCode, listInput);

	  			ObservableList<ClassStudent> classStudentList = FXCollections.observableArrayList();
	  			for (int i = 0; i < data1.length; i++) {
	  				classStudentList.add(new ClassStudent(data1[i]));
	  			}
	  			
	  			colStudentName.setCellValueFactory(new PropertyValueFactory<ClassStudent, String>("studentName"));
	  			tvClassStudent.setItems(classStudentList);
			} else {
				emptyWarning3.setText("请输入正确的班级id!");
			}
  			
  		}
  	    
  	    //录入学生信息之 添加学生
  	  @FXML
      void studentInfoAddClick(ActionEvent event) {

  		emptyWarning8.setText("");
		clearClientParameter();
			
//		loginData.add(id.getId());
//		loginData.add(id.getPassword());
		data1 = new String[0];
		
		listInput.add(studentInfoTfId.getText()); 
		listInput.add(studentInfoTfName.getText()); 
		listInput.add(studentInfoTfGender.getText()); 
		listInput.add(studentInfoTfNation.getText()); 
		listInput.add(studentInfoTfEnrollmentY.getText()); 
		listInput.add(studentInfoTfBirth.getText()); 
		listInput.add(studentInfoTfHomeA.getText()); 
		listInput.add(studentInfoTfFamilyT.getText()); 
		listInput.add(studentInfoTfPolitics.getText()); 
		listInput.add(studentInfoTfRanking.getText()); 
		listInput.add(studentInfoTfDormitory.getText()); 
		listInput.add(studentInfoTfDropOut.getText()); 
		btnCode = "14";
			
		client(btnCode, listInput);
		if (data1.length > 1) {
	  		emptyWarning8.setText(data1[0]);
		}
  		  
      }
  	 	    
	  //录入教师信息之 添加教师
	  	  @FXML
	      void teacherInfoAddClick(ActionEvent event) {

	  		emptyWarning9.setText("");
			clearClientParameter();
				
//			loginData.add(id.getId());
//			loginData.add(id.getPassword());
			data1 = new String[0];
			
			listInput.add(teacherInfoTfId.getText()); 
			listInput.add(teacherInfoTfName.getText()); 
			listInput.add(teacherInfoTfGender.getText()); 
			listInput.add(teacherInfoTfNation.getText()); 
			listInput.add(teacherInfoTfPolitics.getText()); 
			listInput.add(teacherInfoTfEducation.getText()); 
			listInput.add(teacherInfoTfBirth.getText()); 
			listInput.add(teacherInfoTfPhone.getText()); 
			listInput.add(teacherInfoTfEmail.getText());
			btnCode = "15";
				
			client(btnCode, listInput);
			if (data1.length > 1) {
		  		emptyWarning9.setText(data1[0]);
			}
	  		  
	      }
	    

	    
	    //传递登陆者id
	    @Override
  		public void initialize(URL arg0, ResourceBundle arg1) {
  			// TODO Auto-generated method stub
  			System.out.println(id.getId() + "   " + id.getPassword());
  		}
  		
  		public static void setText(String idTxt, String passwordTxt) {
  			id.setText(idTxt, passwordTxt);
  	    }	
		
  		
  		
  		
  	//为了改变字符串数组的大小
  		public void addToData1(String element) { //element为新元素
  			String[] array = new String[data1.length + 1]; //创建一个新数组 
  			for (int i = 0; i < data1.length; i++) {
  				array[i] = data1[i];
  			}
  			//将新元素添加到新数组
  			array[data1.length] = element;
  			data1 = array;
  			
  		}
  		
  		//清空用过的client参数
  		public void clearClientParameter() {
  			loginData.clear();
  			listInput.clear();
  		}
  		
  		
  		
  		@SuppressWarnings("unchecked")
		public void client(String i, ArrayList<String> list) {
  			try {
  				//实例化对象用于读取配置文件内容
  				ClientThread clientThread = new ClientThread();
  				
  				//1.创建客户端Socket，指定服务器地址和端口
  				//如果不是本机链接，则将localhost改为链接服务器的IP
  				//从配置文件中获取IP和端口
  				String ip = clientThread.readProperties("IP");
  				String port = clientThread.readProperties("PORT");
  				
  	            Socket socket = new Socket(ip, Integer.parseInt(port));
  	            //2.获取输出流，向服务器端发送信息
  	            OutputStream os = socket.getOutputStream(); //字节输出流
  	            InputStream is = socket.getInputStream(); //字节输入流

  	            //创建输出对象流，将对象输出
  	            ObjectOutputStream oos = new ObjectOutputStream(os);
  	            ObjectInputStream ois = new ObjectInputStream(is);
  	            
  	            oos.writeObject(i);
  	        	oos.writeObject(list);
  	        	ArrayList<Object> object =  (ArrayList<Object>) ois.readObject();
  	        	System.out.println(object);
  	        	
  	        	//这里要写一个switch函数去读取i，看看是几号，然后将传送回来的数据进行划分，存放入data1中
  	        	switch (i) {
  	        	//录入学生信息
  	        	case "14":
  	        		if (!object.equals(null)) {
  	        			data1 = new String[object.size()];
  						for (int x = 0; x < object.size(); x++) {
  							data1[x] = object.get(x).toString();
  						}
  	        		}
  	        		
					break;

				//录入教师信息
  	        	case "15":
  	        		if (!object.equals(null)) {
  	        			data1 = new String[object.size()];
  						for (int x = 0; x < object.size(); x++) {
  							data1[x] = object.get(x).toString();
  						}
  	        		}
  	        		
	            	break;
  	        	
	            //班级学生名单
  	        	case "16":
  	        		data1 = new String[object.size()];
					for (int x = 0; x < object.size(); x++) {
						data1[x] = object.get(x).toString();
					}
					break;
  	        	
				//班级学业情况
  	        	case "17":
  	        		data2 = new String[object.size()][((ArrayList<String>) object.get(0)).size()];
					for (int x = 0; x < object.size(); x++) {
						for (int j = 0; j < ((ArrayList<String>) object.get(x)).size(); j++) {
							data2[x][j] = ((ArrayList<String>) object.get(x)).get(j).toString();
						}
					}
					break;
  	        	
				//总体考勤情况
  	        	case "18":
  	        		data2 = new String[object.size()][((ArrayList<String>) object.get(0)).size()];
					for (int x = 0; x < object.size(); x++) {
						for (int j = 0; j < ((ArrayList<String>) object.get(x)).size(); j++) {
							data2[x][j] = ((ArrayList<String>) object.get(x)).get(j).toString();
						}
					}
	            	break;
  	        	
	            //年级学业情况
  	        	case "19":
  	        		data2 = new String[object.size()][((ArrayList<String>) object.get(0)).size()];
					for (int x = 0; x < object.size(); x++) {
						for (int j = 0; j < ((ArrayList<String>) object.get(x)).size(); j++) {
							data2[x][j] = ((ArrayList<String>) object.get(x)).get(j).toString();
						}
					}
  	        		break;
  	        		
  	        	default:
  	        		break;
  	        	}
  	        	
  	        	socket.close();
  			} catch (UnknownHostException e) {
  	            e.printStackTrace();
  	        } catch (IOException e) {
  	            e.printStackTrace();
  	        } catch (ClassNotFoundException e) {
  				// TODO 自动生成的 catch 块
  				e.printStackTrace();
  			}
  		}
  		
  		public String readProperties(String key) throws IOException {
  			
  			String filepath = "serve/socket.properties";
  			InputStream in = ClientThread.class.getClassLoader().getResourceAsStream(filepath);
  			Properties properties = new Properties();
  			properties.load(in);
  			String answer = properties.getProperty(key);
  			in.close();
  			in = null;

  			return answer;
  		}
	
}
