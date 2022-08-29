package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.attribute.standard.Severity;

import dao.CommentDAOImpl;
import service.CommentService;
import service.CommentServiceImpl;
import service.CommonService;
import service.CommonServiceImpl;
import service.LoginService;
import service.LoginServiceImpl;
import service.MyPageService;
import service.MyPageServiceImpl;
import service.TestService;
import service.TestServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestController extends Controller implements Initializable {

	private Parent root;
	private CommonService cs;
	private TestService ts;
	private CommentService cms;
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
		ts=new TestServiceImpl();
		cms=new CommentServiceImpl();
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
	/////////////////////////////////////////////////////QuizController////////////////////////////////////////////////////////////
	public void StartQuiz(ActionEvent event) { //테스트 시작하기(첫번째 문제로 이동)
		Stage s=new Stage();
		s.setTitle("TestVillage");
		TestResult.yesSum=0; //테스트 보기 전, yesSum값을 0으로 바꾼다
		switch(TestResult.testNum) { //사용자가 보려고 하는 테스트의 종류가 무엇인가?
		case 1:	
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test1Quiz1Form.fxml");
			cs.windowClose(event); break;
		case 2: 
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test2Quiz1Form.fxml");
			cs.windowClose(event); break;
		case 3: 
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test3Quiz1Form.fxml");
			cs.windowClose(event); break;	
		}
	}//StartQuiz
	
	public void Quiz2(ActionEvent event) { //두번째 문제로 이동
		Stage s=new Stage();
		s.setTitle("TestVillage");
		RadioButton yesRb=null;
		switch(TestResult.testNum) { //사용자가 보려고 하는 테스트의 종류가 무엇인가?
		case 1:	
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz1의 답변 저장
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test1Quiz2Form.fxml"); //다음 페이지로 이동
			cs.windowClose(event);
			break;
		case 2: 
			setRoot(root); // Test2Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //총 yes 선택 갯수에 1 더함
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test2Quiz2Form.fxml");
			cs.windowClose(event); break;
			
		case 3: 
			setRoot(root); // Test3Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //총 yes 선택 갯수에 1 더함
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test3Quiz2Form.fxml");
			cs.windowClose(event); break;	
		}//end switch
	}//Quiz2
	
	public void Quiz3(ActionEvent event) { //세번째 문제로 이동
		Stage s=new Stage();
		s.setTitle("TestVillage");
		RadioButton yesRb=null;
		switch(TestResult.testNum) { //사용자가 보려고 하는 테스트의 종류가 무엇인가?
		case 1:	
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz2의 답변 저장
			}//end if

			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test1Quiz3Form.fxml"); //다음 페이지로 이동
			cs.windowClose(event);
			break;
		case 2: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //총 yes 선택 갯수에 1 더함
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test2Quiz3Form.fxml");
			cs.windowClose(event); break;
			
		case 3: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //총 yes 선택 갯수에 1 더함
			}//end if

			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test3Quiz3Form.fxml");
			cs.windowClose(event); break;	
		}//end switch
	}//Quiz3
	
	public void Quiz4(ActionEvent event) { //네번째 문제로 이동
		Stage s=new Stage();
		s.setTitle("TestVillage");
		RadioButton yesRb=null;
		switch(TestResult.testNum) { //사용자가 보려고 하는 테스트의 종류가 무엇인가?
		case 1:	
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz3의 답변 저장
			}//end if

			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test1Quiz4Form.fxml"); //다음 페이지로 이동
			cs.windowClose(event);
			break;
		case 2: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++;
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test2Quiz4Form.fxml");
			cs.windowClose(event); break;
			
		case 3: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++;
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test3Quiz4Form.fxml");
			cs.windowClose(event); break;	
		}//end switch
	}//Quiz4
	
	public void Quiz5(ActionEvent event) { //다섯번째 문제로 이동
		Stage s=new Stage();
		s.setTitle("TestVillage");
		RadioButton yesRb=null;
		switch(TestResult.testNum) { //사용자가 보려고 하는 테스트의 종류가 무엇인가?
		case 1:	
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz4의 답변 저장
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test1Quiz5Form.fxml"); //다음 페이지로 이동
			cs.windowClose(event);
			break;
		case 2: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++;
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test2Quiz5Form.fxml");
			cs.windowClose(event); break;
			
		case 3: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++;
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test3Quiz5Form.fxml");
			cs.windowClose(event); break;	
		}//end switch
	}//Quiz5
	
	public void Quiz6(ActionEvent event) { //여섯번째 문제로 이동
		Stage s=new Stage();
		s.setTitle("TestVillage");
		RadioButton yesRb=null;
		switch(TestResult.testNum) { //사용자가 보려고 하는 테스트의 종류가 무엇인가?
		case 1:	
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz5의 답변 저장
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test1Quiz6Form.fxml"); //다음 페이지로 이동
			cs.windowClose(event);
			break;
		case 2: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++;
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test2Quiz6Form.fxml");
			cs.windowClose(event); 
			break;
		case 3: 
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++;
			}//end if
			cs=new CommonServiceImpl();
			cs.showWindow(s, "../resources/fxml/test/Test3Quiz6Form.fxml");
			cs.windowClose(event); 
			break;	
		}//end switch
	}//Quiz6
	
	public void Result(ActionEvent event) { //6번째 문제
		
		Stage s=new Stage();
		s.setTitle("TestVillage");
		RadioButton yesRb=null;
		
		Member m=new Member(); //db에 저장하기 위한 객체 생성
		
		switch(TestResult.testNum) { //사용자가 보려고 하는 테스트의 종류가 무엇인가?
		case 1:	
			setRoot(root); // Test1Quiz 페이지 root 지정
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz6의 답변 저장
			}//end if
			//사용자가 로그인 중인가?
			if(Server.loginFlag) { //로그인 중이라면
				switch(TestResult.yesSum) { //사용자가 yes라고 말한 갯수에 따라 결과가 달라진다
				case 6: 
					cs.showWindow(s, "../resources/fxml/test/Test1Res1Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest1Res(1); //타고난 다이어트 천재
					TestResult.result=1;
					//사용자의 테스트 결과를 db 테이블 test1percent에 저장한다 (전체 선택된 수 / 퍼센테이지 계산 테이블)
					ts.modifyTest1Res1Percent();
					break;
				case 5: 
				case 4: 
				case 3: 
					cs.showWindow(s, "../resources/fxml/test/Test1Res2Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest1Res(2); //희망이 보이는 다이어터
					TestResult.result=2;
					//사용자의 테스트 결과를 test2percent에 저장한다
					ts.modifyTest1Res2Percent();
					break;
				case 2: 
				case 1: 
				case 0: 
					cs.showWindow(s, "../resources/fxml/test/Test1Res2Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest1Res(3); //새싹 다이어터
					TestResult.result=3;
					//사용자의 테스트 결과를 test3percent에 저장한다
					ts.modifyTest1Res3Percent();
					break;
				}//end switch 
				//사용자의 테스트 결과를 사용자 db에 저장한다
				ts.modifyTest1Res(m);
				
				
			}else { //사용자가 비회원이라면 
				//TestResult 클래스에 결과를 저장
				switch(TestResult.yesSum) {
				case 6: 
					TestResult.result=1;
					ts.modifyTest1Res1Percent();
					break;
				case 5: 
				case 4: 
				case 3: 
					TestResult.result=2;
					ts.modifyTest1Res2Percent();
					break;
				case 2: 
				case 1: 
				case 0: 
					TestResult.result=3;
					ts.modifyTest1Res3Percent();
					break;
				}//end switch
				//로그인할 것인지 아니면 그냥 결과를 볼 것인지 물어보는 페이지로 이동
				Stage s2=new Stage();
				cs.showWindow(s2, "../resources/fxml/test/TestResLoginForm.fxml");
				cs.windowClose(event);
			}//end else
			break;
		case 2: 
			setRoot(root); // 
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz6의 답변 저장
			}//end if
			//사용자가 로그인 중인가?
			if(Server.loginFlag) { //로그인 중이라면
				switch(TestResult.yesSum) { //사용자가 yes라고 말한 갯수에 따라 결과가 달라진다
				case 6: 
					cs.showWindow(s, "../resources/fxml/test/Test2Res1Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest2Res(1); //타고난 다이어트 천재
					TestResult.result=1;
					//사용자의 테스트 결과를 db 테이블 test1percent에 저장한다 (전체 선택된 수 / 퍼센테이지 계산 테이블)
					ts.modifyTest2Res1Percent();
					break;
				case 5: 
				case 4: 
				case 3: 
					cs.showWindow(s, "../resources/fxml/test/Test2Res2Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest2Res(2); //희망이 보이는 다이어터
					TestResult.result=2;
					//사용자의 테스트 결과를 test2percent에 저장한다
					ts.modifyTest2Res2Percent();
					break;
				case 2: 
				case 1: 
				case 0: 
					cs.showWindow(s, "../resources/fxml/test/Test2Res3Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest2Res(3); //새싹 다이어터
					TestResult.result=3;
					//사용자의 테스트 결과를 test3percent에 저장한다
					ts.modifyTest2Res3Percent();
					break;
				}//end switch 
				//사용자의 테스트 결과를 사용자 db에 저장한다
				ts.modifyTest2Res(m);
				
				
			}else { //사용자가 비회원이라면 
				//TestResult 클래스에 결과를 저장
				switch(TestResult.yesSum) {
				case 6: 
					TestResult.result=1;
					ts.modifyTest2Res1Percent();
					break;
				case 5: 
				case 4: 
				case 3: 
					TestResult.result=2;
					ts.modifyTest2Res2Percent();
					break;
				case 2: 
				case 1: 
				case 0: 
					TestResult.result=3;
					ts.modifyTest2Res3Percent();
					break;
				}//end switch
				//로그인할 것인지 아니면 그냥 결과를 볼 것인지 물어보는 페이지로 이동
				Stage s2=new Stage();
				cs.showWindow(s2, "../resources/fxml/test/TestResLoginForm.fxml");
				cs.windowClose(event);
			}//end else
			break;
		case 3: 
			setRoot(root); // 
			yesRb=(RadioButton) root.lookup("#yesRb");
			if(yesRb.isSelected()) {
				TestResult.yesSum++; //Quiz6의 답변 저장
			}//end if
			//사용자가 로그인 중인가?
			if(Server.loginFlag) { //로그인 중이라면
				switch(TestResult.yesSum) { //사용자가 yes라고 말한 갯수에 따라 결과가 달라진다
				case 6: 
					cs.showWindow(s, "../resources/fxml/test/Test3Res1Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest2Res(1); //타고난 다이어트 천재
					TestResult.result=1;
					//사용자의 테스트 결과를 db 테이블 test1percent에 저장한다 (전체 선택된 수 / 퍼센테이지 계산 테이블)
					ts.modifyTest3Res1Percent();
					break;
				case 5: 
				case 4: 
				case 3: 
					cs.showWindow(s, "../resources/fxml/test/Test3Res2Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest2Res(2); //희망이 보이는 다이어터
					TestResult.result=2;
					//사용자의 테스트 결과를 test2percent에 저장한다
					ts.modifyTest3Res2Percent();
					break;
				case 2: 
				case 1: 
				case 0: 
					cs.showWindow(s, "../resources/fxml/test/Test3Res3Form.fxml");
					cs.windowClose(event); //6번째 질문 창 닫기
					m.setTest2Res(3); //새싹 다이어터
					TestResult.result=3;
					//사용자의 테스트 결과를 test3percent에 저장한다
					ts.modifyTest3Res3Percent();
					break;
				}//end switch 
				//사용자의 테스트 결과를 사용자 db에 저장한다
				ts.modifyTest3Res(m);
				
				
			}else { //사용자가 비회원이라면 
				//TestResult 클래스에 결과를 저장
				switch(TestResult.yesSum) {
				case 6: 
					TestResult.result=1;
					ts.modifyTest3Res1Percent();
					break;
				case 5: 
				case 4: 
				case 3: 
					TestResult.result=2;
					ts.modifyTest3Res2Percent();
					break;
				case 2: 
				case 1: 
				case 0: 
					TestResult.result=3;
					ts.modifyTest3Res3Percent();
					break;
				}//end switch
				//로그인할 것인지 아니면 그냥 결과를 볼 것인지 물어보는 페이지로 이동
				Stage s2=new Stage();
				cs.showWindow(s2, "../resources/fxml/test/TestResLoginForm.fxml");
				cs.windowClose(event);
			}//end else
			break;	
		}//end switch
	}//Result
	

	//비회원 사용자가 로그인할 것인지, 그냥 결과를 볼 것인지 선택
	//로그인 선택
	public void loginForm(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		cs.showWindow(s, "../resources/fxml/LoginForm.fxml");
		cs.windowClose(event);
	}//login

	//그냥 결과보기 선택
	public void resultForm(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		switch(TestResult.testNum) {
		case 1 :
			switch(TestResult.result) {
			case 1: 
				cs.showWindow(s, "../resources/fxml/test/Test1Res1Form.fxml");
				cs.windowClose(event); 
				break;
			case 2:
				cs.showWindow(s, "../resources/fxml/test/Test1Res2Form.fxml");
				cs.windowClose(event); 
				break;
			case 3: 
				cs.showWindow(s, "../resources/fxml/test/Test1Res3Form.fxml");
				cs.windowClose(event); 
				break;
			}//end switch
			break;
		case 2 : 
			switch(TestResult.result) {
			case 1: 
				cs.showWindow(s, "../resources/fxml/test/Test2Res1Form.fxml");
				cs.windowClose(event); 
				break;
			case 2:
				cs.showWindow(s, "../resources/fxml/test/Test2Res2Form.fxml");
				cs.windowClose(event); 
				break;
			case 3: 
				cs.showWindow(s, "../resources/fxml/test/Test2Res3Form.fxml");
				cs.windowClose(event); 
				break;
			}//end switch
			break;
		case 3 : 
			switch(TestResult.result) {
			case 1: 
				cs.showWindow(s, "../resources/fxml/test/Test3Res1Form.fxml");
				cs.windowClose(event); 
				break;
			case 2:
				cs.showWindow(s, "../resources/fxml/test/Test3Res2Form.fxml");
				cs.windowClose(event); 
				break;
			case 3: 
				cs.showWindow(s, "../resources/fxml/test/Test3Res3Form.fxml");
				cs.windowClose(event); 
				break;
			}//end switch
			break;
		}//end switch
	}//resultForm
	
	//다른 유형 보러가기
	public void AllRes(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		Parent root=null;
		TestPercent tp=new TestPercent();
		
		switch(TestResult.testNum) {
		case 1 : 
			
			root=cs.showWindow(s, "../resources/fxml/test/Test1AllResForm.fxml");
			cs.windowClose(event);
			//선택 비율 계산해서 뿌리기
			
			tp=ts.selectTest1Res();
			
			//percent1Tf
			Label percent1Tf= (Label) root.lookup("#percent1Tf");
			Label percent2Tf= (Label) root.lookup("#percent2Tf");
			Label percent3Tf= (Label) root.lookup("#percent3Tf");
			
			percent1Tf.setText( Integer.toString( tp.getRes1Percent() )   );
			percent2Tf.setText(	Integer.toString( tp.getRes2Percent() ) );
			percent3Tf.setText(	Integer.toString( tp.getRes3Percent() ) );
			
			break;
		case 2 :
			root=cs.showWindow(s, "../resources/fxml/test/Test2AllResForm.fxml");
			cs.windowClose(event);
			//선택 비율 계산해서 뿌리기
			tp=ts.selectTest2Res();
			
			//percent1Tf
			percent1Tf= (Label) root.lookup("#percent1Tf");
			percent2Tf= (Label) root.lookup("#percent2Tf");
			percent3Tf= (Label) root.lookup("#percent3Tf");
			
			percent1Tf.setText( Integer.toString( tp.getRes1Percent() )   );
			percent2Tf.setText(	Integer.toString( tp.getRes2Percent() ) );
			percent3Tf.setText(	Integer.toString( tp.getRes3Percent() ) );
			break;
		case 3 :
			root=cs.showWindow(s, "../resources/fxml/test/Test3AllResForm.fxml");
			cs.windowClose(event);
			//선택 비율 계산해서 뿌리기
			tp=ts.selectTest3Res();
			
			//percent1Tf
			percent1Tf= (Label) root.lookup("#percent1Tf");
			percent2Tf= (Label) root.lookup("#percent2Tf");
			percent3Tf= (Label) root.lookup("#percent3Tf");
			
			percent1Tf.setText( Integer.toString( tp.getRes1Percent() )  );
			percent2Tf.setText(	Integer.toString( tp.getRes2Percent() ) );
			percent3Tf.setText(	Integer.toString( tp.getRes3Percent() ) );
			break;
		}//end switch
	}//AllRes
	
	//전체결과 보기 후 다시 결과 페이지로 돌아가기
	public void BackRes(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		switch(TestResult.testNum) {
		case 1 : 
			switch(TestResult.result) {
			case 1 : 
				cs.showWindow(s, "../resources/fxml/test/Test1Res1Form.fxml");
				cs.windowClose(event);
				break;
			case 2 :
				cs.showWindow(s, "../resources/fxml/test/Test1Res2Form.fxml");
				cs.windowClose(event);
				break;
			case 3 :
				cs.showWindow(s, "../resources/fxml/test/Test1Res3Form.fxml");
				cs.windowClose(event);
				break;
			}//end switch
			break;
		case 2 : 
			switch(TestResult.result) {
			case 1 : 
				cs.showWindow(s, "../resources/fxml/test/Test2Res1Form.fxml");
				cs.windowClose(event);
				break;
			case 2 :
				cs.showWindow(s, "../resources/fxml/test/Test2Res2Form.fxml");
				cs.windowClose(event);
				break;
			case 3 :
				cs.showWindow(s, "../resources/fxml/test/Test2Res3Form.fxml");
				cs.windowClose(event);
				break;
			}//end switch
			break;
		case 3 : 
			switch(TestResult.result) {
			case 1 : 
				cs.showWindow(s, "../resources/fxml/test/Test3Res1Form.fxml");
				cs.windowClose(event);
				break;
			case 2 :
				cs.showWindow(s, "../resources/fxml/test/Test3Res2Form.fxml");
				cs.windowClose(event);
				break;
			case 3 :
				cs.showWindow(s, "../resources/fxml/test/Test3Res3Form.fxml");
				cs.windowClose(event);
				break;
			}//end switch
			break;
		}//end switch
	}//backRes
	
	//댓글 쓰러가기 버튼 눌렀을 경우
	public void CommentForm(ActionEvent event) {
		Stage s=new Stage();
		s.setTitle("TestVillage");
		Parent root=null;
		List<Comment> commentList=new ArrayList<>();
		switch(TestResult.testNum) {
		case 1: 
			//댓글 가져와서 뿌리기 (최근2건만)
			root=cs.showWindow(s, "../resources/fxml/CommentTest.fxml");
			cs.windowClose(event);
			commentList=cms.selectCommentListTest1();
			
			Label idLbl1=(Label) root.lookup("#idLbl1");
			Label resLbl1=(Label) root.lookup("#resLbl1");
			Label contentLbl1=(Label) root.lookup("#contentLbl1");
			
			Label idLbl2=(Label) root.lookup("#idLbl2");
			Label resLbl2=(Label) root.lookup("#resLbl2");
			Label contentLbl2=(Label) root.lookup("#contentLbl2");
			
			switch (commentList.size()){ //댓글 table에 댓글이 몇개나 달렸나?
			case 0:	
				contentLbl1.setText("작성된 댓글이 업습니다");
				break;
			case 2:
				idLbl1.setText(commentList.get(0).getId());
				resLbl1.setText(commentList.get(0).getRes());
				contentLbl1.setText(commentList.get(0).getContent());
			case 1:
				idLbl2.setText(commentList.get(1).getId());
				resLbl2.setText(commentList.get(1).getRes());
				contentLbl2.setText(commentList.get(1).getContent());
				break;
			}//end switch
			
			
			break;
		case 2:
			//댓글 가져와서 뿌리기 (최근2건만)
			//댓글 가져와서 뿌리기 (최근2건만)
			root=cs.showWindow(s, "../resources/fxml/CommentTest.fxml");
			cs.windowClose(event);
			commentList=cms.selectCommentListTest2();
			
			idLbl1=(Label) root.lookup("#idLbl1");
			resLbl1=(Label) root.lookup("#resLbl1");
			contentLbl1=(Label) root.lookup("#contentLbl1");
			
			idLbl2=(Label) root.lookup("#idLbl2");
			resLbl2=(Label) root.lookup("#resLbl2");
			contentLbl2=(Label) root.lookup("#contentLbl2");
			
			switch (commentList.size()){ //댓글 table에 댓글이 몇개나 달렸나?
			case 0:	
				contentLbl1.setText("작성된 댓글이 업습니다");
				break;
			case 2:
				idLbl1.setText(commentList.get(0).getId());
				resLbl1.setText(commentList.get(0).getRes());
				contentLbl1.setText(commentList.get(0).getContent());
			case 1:
				idLbl2.setText(commentList.get(1).getId());
				resLbl2.setText(commentList.get(1).getRes());
				contentLbl2.setText(commentList.get(1).getContent());
				break;
			}//end switch

			break;
		case 3:
			//댓글 가져와서 뿌리기 (최근2건만)
			root=cs.showWindow(s, "../resources/fxml/CommentTest.fxml");
			cs.windowClose(event);
			commentList=cms.selectCommentListTest2();
			
			idLbl1=(Label) root.lookup("#idLbl1");
			resLbl1=(Label) root.lookup("#resLbl1");
			contentLbl1=(Label) root.lookup("#contentLbl1");
			
			idLbl2=(Label) root.lookup("#idLbl2");
			resLbl2=(Label) root.lookup("#resLbl2");
			contentLbl2=(Label) root.lookup("#contentLbl2");
			
			switch (commentList.size()){ //댓글 table에 댓글이 몇개나 달렸나?
			case 0:	
				contentLbl1.setText("작성된 댓글이 업습니다");
				break;
			case 2:
				idLbl1.setText(commentList.get(0).getId());
				resLbl1.setText(commentList.get(0).getRes());
				contentLbl1.setText(commentList.get(0).getContent());
			case 1:
				idLbl2.setText(commentList.get(1).getId());
				resLbl2.setText(commentList.get(1).getRes());
				contentLbl2.setText(commentList.get(1).getContent());
				break;
			}//end switch

			break;
		}//end switch
	}//commentForm
	
	
	
	
	
}//class
