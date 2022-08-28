package controller;

import java.net.URL;
import java.util.ResourceBundle;

import service.CommonService;
import service.CommonServiceImpl;
import service.LoginService;
import service.LoginServiceImpl;
import service.MyPageService;
import service.MyPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Controller implements Initializable {

	private Parent root;
	private CommonService cs;
	private LoginService ls;
	private MainController mc;
	private MyPageService mps;
	
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
		ls=new LoginServiceImpl();
		mc=new MainController();
		mps=new MyPageServiceImpl();
	}//initialize

	@Override
	public void OpenHome(ActionEvent event) {
		// TODO Auto-generated method stub
		Stage s=new Stage();
		s.setTitle("TestVillage");
		cs.showWindow(s, "../resources/fxml/MainForm.fxml");
		cs.windowClose(event);
	}//OpenHome

	@Override
	public void OpenMyPage(ActionEvent event) { //마이 페이지로 이동
		// TODO Auto-generated method stub
		//현재 로그인인지 아닌지 구분
		//로그인 상태 : 마이페이지로 이동
		//비로그인 상태 : 로그인 페이지로 이동
		Member m=new Member();
		
		if(Server.loginFlag) { //로그인 flag가 true면 마이페이지로 이동, 아니면 로그인 페이지로 이동
			CommonService cs=new CommonServiceImpl();
			Stage s=new Stage();
			Parent form=cs.showWindow(s, "../resources/fxml/MyPageForm.fxml");
			cs.windowClose(event);
			//////////////////////////아이디에 따른 정보 가져와서 마이페이지에 뿌려주기///////////////////////////////////////
			//ComboBox<String> cmbAge = (ComboBox<String>) form.lookup("#cmbAge");
			
			m=mps.selectMyPage(Server.id); //회원의 정보를 가져온다
			
			Label idLbl=(Label) form.lookup("#idLbl");
			Label emailLbl=(Label) form.lookup("#emailLbl");
			Label test1ResLbl=(Label) form.lookup("#test1ResLbl");
			Label test2ResLbl=(Label) form.lookup("#test2ResLbl");
			Label test3ResLbl=(Label) form.lookup("#test3ResLbl");
			//테스트 결과 담을 String
			String test1Res="";
			String test2Res="";
			String test3Res="";
			
			//테스트 결과 번호에 따른 테스트 결과 이름을 부여한다
			switch(m.getTest1Res()){
				case 1 : test1Res="타고난 다이어트 천재"; break;
				case 2 : test1Res="희망이 보이는 다이어터"; break;
				case 3 : test1Res="새싹 다이어터"; break;
				default : test1Res="테스트하러가기";
			}//end switch
			
			switch(m.getTest2Res()){
				case 1 : test2Res="아찔한 파산형"; break;
				case 2 : test2Res="위험한 지름신형"; break;
				case 3 : test2Res="동전 가득 저금통형"; break;
				default : test2Res="테스트하러가기";
			}//end switch
			
			switch(m.getTest3Res()){
				case 1 : test3Res="위풍당당 DNA"; break;
				case 2 : test3Res="리액션왕 DNA"; break;
				case 3 : test3Res="복세편살 DNA"; break;
				default : test3Res="테스트하러가기";
			}//end switch
			
			//회원 정보 마이페이지에 뿌리기
			idLbl.setText(m.getId());
			emailLbl.setText(m.getEmail());
			test1ResLbl.setText(test1Res);
			test2ResLbl.setText(test2Res);
			test3ResLbl.setText(test3Res);

			
		}else {
			//비회원인 상태에서 마이페이지 버튼을 눌렀을 경우
			Server.navigation="mypage";
			CommonService cs=new CommonServiceImpl();
			Stage s=new Stage();
			cs.showWindow(s, "../resources/fxml/LoginForm.fxml");
			cs.windowClose(event);
		}//end else
	}//OpenMyPage
	
	//////////////////////////////////////////////로그인 컨트롤러 메소드///////////////////////////////////////////////
	public void Login(ActionEvent event) {
		//로그인 시도 후 회원 정보 일치하면 원하는 페이지로 이동한다 (Server의 navigation변수를 사용)
		TextField idTf=(TextField) root.lookup("#idTf"); //사용자가 입력한 아이디 가져오기
		PasswordField passPf=(PasswordField) root.lookup("#passPf"); //사용자가 입력한 비밀번호 가져오기
		
		boolean loginFlag=ls.selectLogin(idTf.getText(), passPf.getText()); 
		//로그인 시도 후 회원 정보 불일치하면 에러 메세지를 띄운다
		Stage s=new Stage();
		if(loginFlag) {
			//사용자 정보가 일치하는 경우
			switch(Server.navigation) {
			case "mypage" : cs.showWindow(s, "../resources/fxml/MainForm.fxml") ; break; //메인페이지 버튼 클릭 후 로그인 창 이동 했을 경우 마이페이지로 보내기
			case "membership" : cs.showWindow(s, "../resources/fxml/MainForm.fxml") ; break;
			default: cs.showWindow(s, "../resources/fxml/MainForm.fxml");
			}//end switch
			
			cs.windowClose(event); //로그인 창을 닫는다
		}else {
			//불일치할 경우
			cs.errorBox("로그인 실패", "로그인 실패", "없는 아이디 또는 아이디 및 비밀번호가 다릅니다.");
		}//end else
	}//Login
	
	public void OpenMemberShipForm(ActionEvent event) { //회원가입 페이지로 이동
		Stage s=new Stage();
		s.setTitle("TestVillage");
		Parent root=null;
		root=cs.showWindow(s, "../resources/fxml/MemberShipForm.fxml"); // 회원가입 페이지로 이동
		cs.windowClose(event); // 로그인 페이지를 닫는다

		//myText.setEditable(false); --비활성화 방법 (사용자가 입력하지 못하게 함)
		TextField idTf =(TextField) root.lookup("#idTf");
		idTf.setEditable(false); 
		
		
	}//OpenMemberShipForm
	

}
