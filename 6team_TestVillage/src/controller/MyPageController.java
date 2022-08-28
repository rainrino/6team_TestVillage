package dayeun.controller;

import java.net.URL;
import java.util.ResourceBundle;

import dayeun.service.CommonService;
import dayeun.service.CommonServiceImpl;
import dayeun.service.MyPageService;
import dayeun.service.MyPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyPageController extends Controller implements Initializable {
	private Parent root;
	private CommonService cs;
	private MyPageService mps;
	//private MyPageService mps;
	
	/////////////////////////////////Controller 메소드/////////////////////////////////////////
	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root=root;
	}//setRoot
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cs=new CommonServiceImpl();
		mps=new MyPageServiceImpl();
	
	}//initialize

	@Override
	public void OpenHome(ActionEvent event) {
		// TODO Auto-generated method stub
		Stage s=new Stage();
		cs.showWindow(s, "../fxml/MainForm.fxml");
		cs.windowClose(event);
	}//OpenHome

	@Override
	public void OpenMyPage(ActionEvent event) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//현재 로그인인지 아닌지 구분
		//로그인 상태 : 마이페이지로 이동
		//비로그인 상태 : 로그인 페이지로 이동
		
		if(Server.loginFlag) { //로그인 flag가 true면 마이페이지로 이동, 아니면 로그인 페이지로 이동
			CommonService cs=new CommonServiceImpl();
			Stage s=new Stage();
			cs.showWindow(s, "../fxml/MyPageForm.fxml");
			
			cs.windowClose(event);
		}else {
			//비회원일 때 마이페이지 페이지에 접속 시도
			Server.navigation="mypage"; //로그인 성공 후에 마이페이지 사이트로 이동
			
			CommonService cs=new CommonServiceImpl();
			Stage s=new Stage();
			cs.showWindow(s, "../fxml/LoginForm.fxml");
			
			cs.windowClose(event);
		}//end else
		
	}//OpenMyPage
	
	//////////////////////////////////마이페이지 메소드/////////////////////////////////////
	public void test1ResForm(ActionEvent event) {
		Member m=new Member();
		m=mps.selectMyPage(Server.id); //회원의 정보를 가져온다
		Stage s=new Stage();
		switch(m.getTest1Res()){
		case 1 : 
			cs.showWindow(s, "../fxml/test/Test1Res1Form.fxml");
			cs.windowClose(event);
			break;
		case 2 : 
			cs.showWindow(s, "../fxml/test/Test1Res2Form.fxml");
			cs.windowClose(event);
			break;
		case 3 : 
			cs.showWindow(s, "../fxml/test/Test1Res3Form.fxml");
			cs.windowClose(event);
			break;
		default : 
			cs.showWindow(s, "../fxml/test/Test1Form.fxml");
			cs.windowClose(event);
		}//end switch
	};
	
	public void test2ResForm(ActionEvent event) {
		Member m=new Member();
		m=mps.selectMyPage(Server.id); //회원의 정보를 가져온다
		Stage s=new Stage();
		switch(m.getTest2Res()){
		case 1 : 
			cs.showWindow(s, "../fxml/test/Test2Res1Form.fxml");
			cs.windowClose(event);
			break;
		case 2 : 
			cs.showWindow(s, "../fxml/test/Test2Res2Form.fxml");
			cs.windowClose(event);
			break;
		case 3 : 
			cs.showWindow(s, "../fxml/test/Test2Res3Form.fxml");
			cs.windowClose(event);
			break;
		default : 
			cs.showWindow(s, "../fxml/test/Test2Form.fxml");
			cs.windowClose(event);
		}//end switch
	};
	
	public void test3ResForm(ActionEvent event) {
		Member m=new Member();
		m=mps.selectMyPage(Server.id); //회원의 정보를 가져온다
		Stage s=new Stage();
		switch(m.getTest3Res()){
		case 1 : 
			cs.showWindow(s, "../fxml/test/Test3Res1Form.fxml");
			cs.windowClose(event);
			break;
		case 2 : 
			cs.showWindow(s, "../fxml/test/Test3Res2Form.fxml");
			cs.windowClose(event);
			break;
		case 3 : 
			cs.showWindow(s, "../fxml/test/Test3Res3Form.fxml");
			cs.windowClose(event);
			break;
		default : 
			cs.showWindow(s, "../fxml/test/Test3Form.fxml");
			cs.windowClose(event);
		}//end switch
	};
	
	public void LogOut(ActionEvent event) { 
		//로그아웃 메소드
		//서버 로그아웃
		Server.loginFlag=false; //비회원 상태로 전환
		Server.id=""; //아이디 빈칸으로 전환
		
		//메인 페이지로 이동
		Stage s=new Stage();
		cs.showWindow(s, "../fxml/MainForm.fxml");
		
		//마이페이지 페이지 닫음
		cs.windowClose(event);
	};//LogOut
	
	public void DropChkForm(ActionEvent event) {
		//탈퇴 페이지 이동 메소드
		Stage s=new Stage();
		Parent form=null;
		
		form=cs.showWindow(s, "../fxml/MemberDropWarnForm.fxml");
		
		cs.windowClose(event); //마이페이지 창 닫기 
		
	};//DropChkForm


}//class
