package controller;

import java.net.URL;
import java.util.ResourceBundle;

import service.CommonService;
import service.CommonServiceImpl;
import service.MemberDropService;
import service.MemberDropServiceImpl;
import service.MyPageService;
import service.MyPageServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MemberDropController extends Controller implements Initializable {
	private Parent root;
	
	private CommonService cs;
	private MyPageService mps;
	private MemberDropService mds;
	
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
		mds=new MemberDropServiceImpl();
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
	
	/////////////////////////////////////////////MemberDropController(회원탈퇴) 메소드//////////////////////////////////////////////////
	public void next(ActionEvent event) {  //MemeberDropWarnForm에서 다음 버튼을 클릭할 시,
		//탈퇴 설문조사 comboBOX 생성
		Parent form=null;
		Stage s=new Stage();
		s.setTitle("TestVillage");
		form=cs.showWindow(s, "../resources/fxml/MemberDropSurveyForm.fxml"); //회원탈퇴 이유 설문조사 페이지로 이동
		String[] items = {"자주 사용하지 않습니다.", "사용할 때 오류가 있습니다.", "개인정보 노출이 우려됩니다.", "기타"};
		
		@SuppressWarnings("unchecked")
		ComboBox<String> surveyCb = (ComboBox<String>) form.lookup("#surveyCb");
		
		for(String item : items) {
			surveyCb.getItems().add(item);
		}//end for
		cs.windowClose(event); // 회원탈퇴 경고 페이지 닫기
	}//nextBtn
	

	public void memberDrop(ActionEvent event) { //MemberDropSurveyForm에서 탈퇴하기 버튼을 클릭할 시,
		@SuppressWarnings("unchecked")
		ComboBox<String> surveyCb = (ComboBox<String>) root.lookup("#surveyCb");
		Stage s=new Stage();
		s.setTitle("TestVillage");
		int res=0;
		if(surveyCb.getValue() != null) { //comboBox가 선택되었을 때,
			
			res=mds.dropMember(); //회원탈퇴 메소드 실행
			if(res==1) { //res ==1은 회원탈퇴가 성공적으로 실행된 것
				cs.showWindow(s, "../resources/fxml/MemberDropSuccessForm.fxml");
				cs.windowClose(event); // 회원탈퇴 설문 페이지 닫기
			}else { //회원탈퇴가 이루어지지 않았을 떄 - db 에러
				cs.errorBox("경고", "회원탈퇴 실패", "오류가 발생했습니다. 관리자에게 문의해주세요.");
			}//end else
			
		}else {
			cs.errorBox("경고", "회원탈퇴 실패", "탈퇴 이유가 선택되지 않았습니다"); //comboBox가 선택되지 않았을 때,
		}//end else
		
	}//memberDrop
	
	public void successDrop(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		cs.showWindow(s, "../resources/fxml/MainForm.fxml");
		cs.windowClose(event);
	}//successDrop
	
	
}//class
