package application;

import control.StudentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * @version 2.1
 * @since jdk1.8
 * @return null
 * @throws Exception
 */
public class Main extends Application {
	
	//不用登录直接跳转家长端
	@Override
	public void start(Stage primaryStage) {
		StudentController.setText("123456", "1234");
		try {
			BorderPane root = new BorderPane();	//window
			//getResource() uses '/' to represent the path
			root = FXMLLoader.load(getClass().getResource("/view/studentHome.fxml"));
			Scene scene = new Scene(root);	//scene
			//Style scene
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
			primaryStage.setTitle("数智教育");	//The stage should have a title
			primaryStage.setScene(scene);	//Put the scene on the stage
			primaryStage.show();	//show the stage
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	//原来的版本
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			AnchorPane root = new AnchorPane();	//window
//			//getResource() uses '/' to represent the path
//			root = FXMLLoader.load(getClass().getResource("/view/Login_Select.fxml"));
//			Scene scene = new Scene(root);	//scene
//			//Style scene
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
//			primaryStage.setTitle("数智教育");	//The stage should have a title
//			primaryStage.setScene(scene);	//Put the scene on the stage
//			primaryStage.show();	//show the stage
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
