package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Member;
import controller.Server;
import controller.TestPercent;
import service.CommonService;
import service.CommonServiceImpl;

public class TestDAOImpl implements TestDAO {

	Connection con;
	CommonService comServ;
	
	public TestDAOImpl() {
		// TODO Auto-generated constructor stub
		comServ = new CommonServiceImpl();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.35.195:1521:xe";
			String user = "system";
			String pass = "oracle";
			//System.out.println("오라클연결");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}//QuizDAOImpl


	@Override
	public int modifyTest1Res(Member m) { //다이어트 테스트 결과에 대한 사용자 db저장
		int resMember=0;
		
		String sql = "	update member set  test1res = ? where id=?	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			pstmt.setInt(1, m.getTest1Res());
			pstmt.setString(2, Server.id);
			resMember = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resMember;
	}//modifyTest1Res


	@Override
	public int modifyTest2Res(Member m) { //소비성향 테스트 결과에 대한 사용자 db저장
		int resMember=0;
		
		String sql = "	update member set  test2res = ? where id=?	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			pstmt.setInt(1, m.getTest1Res());
			pstmt.setString(2, Server.id);
			resMember = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		 
		
		return resMember;
	}//modifyTest2Res


	@Override
	public int modifyTest3Res(Member m) { //모임성향 테스트 결과에 대한 사용자 db저장
		int resMember=0;
		
		String sql = "	update member set  test3res = ? where id=?	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			pstmt.setInt(1, m.getTest1Res());
			pstmt.setString(2, Server.id);
			resMember = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resMember;
	}//modifyTest3Res

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int modifyTest1Res1Percent() { //테스트 1에서 결과 1이 선택되었을 때 총 선택 갯수에 추가하기
		int resPercent=0;
		
		String sql = "	update test1percent set  selectRes1 = test1_res1.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest1Res2Percent() {
		int resPercent=0;
		
		String sql = "	update test1percent set  selectRes2 = test1_res2.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest1Res3Percent() {
		int resPercent=0;
		
		String sql = "	update test1percent set  selectRes3 = test1_res3.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest2Res1Percent() {
		int resPercent=0;
		
		String sql = "	update test2percent set  selectRes1 = test2_res1.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest2Res2Percent() {
		int resPercent=0;
		
		String sql = "	update test2percent set  selectRes2 = test2_res2.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest2Res3Percent() {
		int resPercent=0;
		
		String sql = "	update test2percent set  selectRes3 = test2_res3.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest3Res1Percent() {
		int resPercent=0;
		
		String sql = "	update test3percent set  selectRes1 = test3_res1.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest3Res2Percent() {
		int resPercent=0;
		
		String sql = "	update test3percent set  selectRes2 = test3_res2.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}


	@Override
	public int modifyTest3Res3Percent() {
		int resPercent=0;
		
		String sql = "	update test3percent set  selectRes3 = test3_res3.nextval	"; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.set
			resPercent = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		return resPercent;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public TestPercent selectTest1Res() { 
		TestPercent tp=new TestPercent();
		
		try {
			String sql = "	select selectres1, selectres2, selectres3 from test1percent	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//m.setId(rs.getString("id"));
				tp.setSelectRes1(rs.getInt("selectres1"));
				tp.setSelectRes2(rs.getInt("selectres2"));
				tp.setSelectRes3(rs.getInt("selectres3"));
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return tp;
	}


	@Override
	public TestPercent selectTest2Res() {
		TestPercent tp=new TestPercent();
		
		try {
			String sql = "	select selectres1, selectres2, selectres3 from test2percent	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//m.setId(rs.getString("id"));
				tp.setSelectRes1(rs.getInt("selectres1"));
				tp.setSelectRes2(rs.getInt("selectres2"));
				tp.setSelectRes3(rs.getInt("selectres3"));
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return tp;
	}


	@Override
	public TestPercent selectTest3Res() {
		TestPercent tp=new TestPercent();
		
		try {
			String sql = "	select selectres1, selectres2, selectres3 from test3percent	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//m.setId(rs.getString("id"));
				tp.setSelectRes1(rs.getInt("selectres1"));
				tp.setSelectRes2(rs.getInt("selectres2"));
				tp.setSelectRes3(rs.getInt("selectres3"));
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return tp;
	}



}//class
