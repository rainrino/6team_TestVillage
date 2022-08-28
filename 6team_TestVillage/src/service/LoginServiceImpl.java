package service;

import dao.MemberDAO;
import dao.MemberDAOImpl;

public class LoginServiceImpl implements LoginService {

	@Override
	public boolean selectLogin(String id, String pass) {
		// TODO Auto-generated method stub
		boolean loginFlag=false;
		MemberDAO mDAO=new MemberDAOImpl();
		
		loginFlag=mDAO.chkLogin(id, pass);
		
		return loginFlag;
	}//selectLogin

}//class
