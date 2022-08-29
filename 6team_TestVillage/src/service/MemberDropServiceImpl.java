package service;

import dao.MemberDAO;
import dao.MemberDAOImpl;


public class MemberDropServiceImpl implements MemberDropService {

	
	@Override
	public int dropMember() {
		int res=0;
		MemberDAO mDAO=new MemberDAOImpl();
		
		res=mDAO.dropMember();
		return res;
	}//dropMember

}//class
