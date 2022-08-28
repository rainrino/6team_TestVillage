package service;

import java.util.ArrayList;
import java.util.List;

import controller.Comment;
import dao.CommentDAO;
import dao.CommentDAOImpl;

public class CommentServiceImpl implements CommentService{
	CommentDAO cDAO=new CommentDAOImpl();
	
	@Override
	public List<Comment> selectCommentListTest1() {
		// TODO Auto-generated method stub
		List<Comment> commentList=new ArrayList<>();
		commentList=cDAO.selectCommentListTest1();
		return commentList;
	}

	@Override
	public int insertCommentTest1(Comment c) {
		// TODO Auto-generated method stub
		int res=0;
		res=cDAO.insertCommentTest1(c);
		return res;
	}

	@Override
	public List<Comment> selectAllCommentListTest1() {
		// TODO Auto-generated method stub
		List<Comment> commentList=new ArrayList<>();
		commentList=cDAO.selectAllCommentListTest1();
		return commentList;
	}

	@Override
	public List<Comment> selectCommentListTest2() {
		List<Comment> commentList=new ArrayList<>();
		commentList=cDAO.selectCommentListTest2();
		return commentList;
	}

	@Override
	public int insertCommentTest2(Comment c) {
		int res=0;
		res=cDAO.insertCommentTest2(c);
		return res;
	}

	@Override
	public List<Comment> selectAllCommentListTest2() {
		List<Comment> commentList=new ArrayList<>();
		commentList=cDAO.selectAllCommentListTest2();
		return commentList;
	}

	@Override
	public List<Comment> selectCommentListTest3() {
		List<Comment> commentList=new ArrayList<>();
		commentList=cDAO.selectCommentListTest3();
		return commentList;
	}

	@Override
	public int insertCommentTest3(Comment c) {
		int res=0;
		res=cDAO.insertCommentTest3(c);
		return res;
	}

	@Override
	public List<Comment> selectAllCommentListTest3() {
		List<Comment> commentList=new ArrayList<>();
		commentList=cDAO.selectAllCommentListTest3();
		return commentList;
	}

}
