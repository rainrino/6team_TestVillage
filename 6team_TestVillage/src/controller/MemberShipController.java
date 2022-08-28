package controller;

import java.net.URL;
import java.util.ResourceBundle;

import service.CommonService;
import service.CommonServiceImpl;
import service.LoginService;
import service.LoginServiceImpl;
import service.MemberShipService;
import service.MemberShipServiceImpl;
import service.MyPageService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MemberShipController extends Controller implements Initializable{

	//myText.setEditable(false); --textfield 비활성화 방법
	private Parent root;
	private CommonService cs;
	private MemberShipService mss;
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
		mss=new MemberShipServiceImpl();
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
			s.setTitle("TestVillage");
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
			s.setTitle("TestVillage");
			cs.showWindow(s, "../resources/fxml/LoginForm.fxml");
			cs.windowClose(event);
		}//end else
	}//OpenMyPage
	
	/////////////////////////////////////////////MemberShipController(회원가입) 메소드 ///////////////////////////////////////////////////////////
	public void LoginDupChkForm(ActionEvent event) { // 아이디 중복 버튼을 눌렀을 때,
		Stage s=new Stage();
		s.setTitle("TestVillage");
		cs.showWindow(s, "../resources/fxml/ChkDupIdForm.fxml"); // 아이디 중복 확인 페이지를 연다
		cs.windowClose(event);
	}//LoginDupChk
	
	
	public void LoginDupChk(ActionEvent event) { // 아이디 중복확인 페이지에서 중복확인 버튼을 눌렀을 때,
		String chkSuccessId="";
		int res=0; //중복 아이디가 있는지 체크한 뒤 결과 값
		setRoot(root); //아이디 중복 체크 페이지로 root 지정
		TextField idChkDupTf= (TextField) root.lookup("#idChkDupTf"); //아이디 중복확인 textfield에 사용자가 넣은 값 가져오기
		
		if(idChkDupTf.getText() == null) { //사용자가 넣은 값이 없다면
			cs.errorBox("경고", "입력오류", "사용하실 아이디를 입력해주세요.");
		}//end if
		
		String chkId=idChkDupTf.getText();
		/////////////////////////////////////////////////////////////////////////특수문자/한글 사용 잡기?
		res=mss.chkDupId(chkId); //아이디 중복검사 db select 메소드 
		
		if(res != 1) { //사용할 수 있는 아이디라면
			cs.errorBox("사용가능 아이디", "사용가능 아이디", "사용 가능한 아이디입니다.");
			cs.windowClose(event); //중복 아이디 페이지 닫기
			
			Stage s=new Stage();
			Parent root=cs.showWindow(s, "../resources/fxml/MemberShipForm.fxml");  
			TextField idTf= (TextField) root.lookup("#idTf"); 
			idTf.setText(chkId); //회원가입 아이디 textField에 사용하려는 아이디 입력
		}else { //사용할 수 없는 아이디라면
			cs.errorBox("사용불가 아이디", "사용불가 아이디", "이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
		}//end else
		
	}//LoginDupChkSuccess
	
	public void Exit(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		cs.windowClose(event); //아이디 중복확인 창을 닫는다
		cs.showWindow(s, "../resources/fxml/MemberShipForm.fxml"); //회원가입 창을 연다
	}//Exit
	
	
	public void insertMember(ActionEvent event) {
		//회원가입 유효성 검사 및 member db insert
		setRoot(root);
		TextField idTf= (TextField) root.lookup("#idTf");
		PasswordField passPf= (PasswordField) root.lookup("#passPf");
		PasswordField passChkPf= (PasswordField) root.lookup("#passChkPf");
		TextField emailTf= (TextField) root.lookup("#emailTf");

		//각 변수에 저장
		String id=idTf.getText();
		String pass=passPf.getText();
		String passChk=passChkPf.getText();
		String email=emailTf.getText();
		if(!pass.equals(passChk)) { //비밀번호-비밀번호 확인이 서로 같지 않다면
			cs.errorBox("회원가입 오류", "회원가입 오류", "비밀번호가 서로 다릅니다. 다시 확인해주세요");
			passPf.clear();
			passChkPf.clear();
			return;
		}//end if
		
		if(pass.length() > 20) { //비밀번호가 20자리를 넘어간다면
			cs.errorBox("회원가입 오류", "회원가입 오류", "비밀번호는 20자리를 넘을 수 없습니다. 다시 확인해주세요.");
			passPf.clear();
			passChkPf.clear();
			return;
		}//end if
		
		if(id==null || id.equals("") ) { //비밀번호가 20자리를 넘어간다면
			cs.errorBox("회원가입 오류", "회원가입 오류", "아이디를 입력해주세요");
			return;
		}//end if
		
		if(pass==null || pass.equals("") ) { //비밀번호가 20자리를 넘어간다면
			cs.errorBox("회원가입 오류", "회원가입 오류", "비밀번호를 입력해주세요");
			return;
		}//end if
		
		if(email==null || email.equals("") ) { //비밀번호가 20자리를 넘어간다면
			cs.errorBox("회원가입 오류", "회원가입 오류", "이메일을 입력해주세요");
			return;
		}//end if
		
		Member m=new Member();
		m.setId(id);
		m.setPass(pass);
		m.setEmail(email);
		m.setDropFlag("false");
		
		int res=0; //insert db 결과값
		
		res=mss.insertMember(m); //insert db 메소드
		
		Stage s=new Stage();
		
		if(res == 1) { // insert 되었을 때 -> 성공적으로 회원가입이 왼료되었을 때
			cs.showWindow(s, "../resources/fxml/MemberShipSuccessForm.fxml"); //회원가입 축하 페이지로 이동
			cs.windowClose(event);
		}else {  // insert 되지 않았을 때
			
			cs.errorBox("회원가입 오류", "회원가입 오류", "문제가 발생했습니다. 관리자에게 문의해주시기 바랍니다.");
			cs.showWindow(s, "../resources/fxml/MainForm.fxml");
			cs.windowClose(event);
			
		}//end else
		
		
	}//insertMember
	
	
	public void login(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		cs.showWindow(s, "../resources/fxml/LoginForm.fxml");
		cs.windowClose(event);
		Server.navigation="membership";
	}//login
	
}//class
