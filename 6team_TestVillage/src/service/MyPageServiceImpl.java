package service;

import controller.Member;
import controller.Server;
import dao.MemberDAO;
import dao.MemberDAOImpl;

public class MyPageServiceImpl implements MyPageService {
	Server server;
	
	@Override
	public Member selectMyPage(String id) { //로그인한 회원의 정보를 출력하는 메소드
		Member m=new Member();
		MemberDAO mDAO=new MemberDAOImpl();
		m=mDAO.selectMyPage(id);
		
		return m;
	}//selectMyPage


}//class
