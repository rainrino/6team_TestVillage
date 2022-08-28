package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Comment;
import service.CommonService;
import service.CommonServiceImpl;

public class CommentDAOImpl implements CommentDAO {

	Connection con;
	CommonService comServ;
	
	public CommentDAOImpl() {
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
	public List<Comment> selectCommentListTest1() {
		// TODO Auto-generated method stub
		List<Comment> commentList=new ArrayList<>();
		
		try {
			String sql = "	select id,res,content,commentnum from commenttable where rownum <=2 order by commentnum desc	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//m.setId(rs.getString("id"));
				Comment c=new Comment();
				c.setId(rs.getString("id"));
				c.setRes(rs.getString("res"));
				c.setContent(rs.getString("content"));
				commentList.add(c);
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		
		return commentList;
	}//selectCommentList

	
	
	@Override
	public int insertCommentTest1(Comment c) { //댓글 입력
		// TODO Auto-generated method stub
		int res=0;
		String sql = "	insert into commenttable values (?, ?, ?, commentNum_seq.nextval )	"; //dropFlag = ture /탈퇴한 회원
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getRes());
			pstmt.setString(3, c.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		return res;
	}//insertComment
	
	@Override
	public List<Comment> selectAllCommentListTest1() { //모든 댓글을 조회하는 메소드 사실은 5개
		List<Comment> commentList=new ArrayList<>();
		int num=0;
		
		try {
			String sql = "	select id,res,content,commentnum from commenttable where rownum <=5 order by commentnum desc	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//m.setId(rs.getString("id"));
				Comment c=new Comment();
				c.setId(rs.getString("id"));
				c.setRes(rs.getString("res"));
				c.setContent(rs.getString("content"));
				commentList.add(c);
				num++;
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return commentList;
	}//selectAllCommentListTest1

	///////////////////////////////////////////////////////////////

	@Override
	public List<Comment> selectCommentListTest2() {
		// TODO Auto-generated method stub
		List<Comment> commentList=new ArrayList<>();
		
		try {
			String sql = "	select id,res,content,commentnum from commenttable2 where rownum <=2 order by commentnum desc	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//m.setId(rs.getString("id"));
				Comment c=new Comment();
				c.setId(rs.getString("id"));
				c.setRes(rs.getString("res"));
				c.setContent(rs.getString("content"));
				commentList.add(c);
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		
		return commentList;
	}//selectCommentListTest2


	@Override
	public int insertCommentTest2(Comment c) {
		// TODO Auto-generated method stub
		int res=0;
		String sql = "	insert into commenttable2 values (?, ?, ?, commentNum_seq.nextval )	"; //dropFlag = ture /탈퇴한 회원
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getRes());
			pstmt.setString(3, c.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		return res;
	}


	@Override
	public List<Comment> selectAllCommentListTest2() {
		List<Comment> commentList=new ArrayList<>();
		int num=0;
		
		try {
			String sql = "	select id,res,content,commentnum from commenttable2 where rownum <=5 order by commentnum desc	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//m.setId(rs.getString("id"));
				Comment c=new Comment();
				c.setId(rs.getString("id"));
				c.setRes(rs.getString("res"));
				c.setContent(rs.getString("content"));
				commentList.add(c);
				num++;
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		
		return commentList;
	}


	@Override
	public List<Comment> selectCommentListTest3() {
		// TODO Auto-generated method stub
		List<Comment> commentList=new ArrayList<>();
		
		try {
			String sql = "	select id,res,content,commentnum from commenttable3 where rownum <=2 order by commentnum desc	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//m.setId(rs.getString("id"));
				Comment c=new Comment();
				c.setId(rs.getString("id"));
				c.setRes(rs.getString("res"));
				c.setContent(rs.getString("content"));
				commentList.add(c);
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		
		return commentList;
	}


	@Override
	public int insertCommentTest3(Comment c) {
		int res=0;
		String sql = "	insert into commenttable3 values (?, ?, ?, commentNum_seq.nextval )	"; //dropFlag = ture /탈퇴한 회원
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getRes());
			pstmt.setString(3, c.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		return res;
	}


	@Override
	public List<Comment> selectAllCommentListTest3() {
		List<Comment> commentList=new ArrayList<>();
		int num=0;
		
		try {
			String sql = "	select id,res,content,commentnum from commenttable3 where rownum <=5 order by commentnum desc	";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//m.setId(rs.getString("id"));
				Comment c=new Comment();
				c.setId(rs.getString("id"));
				c.setRes(rs.getString("res"));
				c.setContent(rs.getString("content"));
				commentList.add(c);
				num++;
			}//end if
			

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		
		return commentList;
	}


	

}
