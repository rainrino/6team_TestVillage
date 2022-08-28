package _main;


import service.CommonService;
import service.CommonServiceImpl;
import controller.Server;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestVillageMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		//////////////////////////////폰트 추가///////////////////////////////////////
        System.setProperty("prism.lcdtext", "false"); // 폰트파일 로드전에 실행                
        Font.loadFont(getClass().getResourceAsStream("../resources/font/NotoSansKR-Medium.otf"), 10); //폰트 추가
        //////////////////////////////서버 초기화////////////////////////////////
        Server.loginFlag=false;
        Server.id="";
        Server.navigation="";
        ///////////////////////////////////////////////////////////////////
		CommonService comServ = new CommonServiceImpl();
		primaryStage.setTitle("TestVillage");
		comServ.showWindow(primaryStage, "../resources/fxml/MainForm.fxml"); //fxml패키지의 MainForm열기
	}//start

	public static void main(String[] args) {
		launch(args);
	}//main
}//class
