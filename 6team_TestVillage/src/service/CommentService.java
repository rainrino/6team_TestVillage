package service;

import java.util.List;

import controller.Comment;

public interface CommentService {
	public List<Comment> selectCommentListTest1(); //db에서 댓글을 가져오는 메소드(최근2개만)
	int insertCommentTest1(Comment c); //테스트1 댓글 테이블에 댓글 입력
	public List<Comment> selectAllCommentListTest1(); //모든 댓글 가져오는 메소드
	/////////////////////////////////////////////////////////////////////////////////
	public List<Comment> selectCommentListTest2();
	int insertCommentTest2(Comment c); //테스트2 댓글 테이블에 댓글 입력
	public List<Comment> selectAllCommentListTest2(); //모든 댓글 가져오는 메소드
	////////////////////////////////////////////////////////////////////////////////
	public List<Comment> selectCommentListTest3();
	int insertCommentTest3(Comment c); //테스트2 댓글 테이블에 댓글 입력
	public List<Comment> selectAllCommentListTest3(); //모든 댓글 가져오는 메소드
	//////////////////////////////////////////////////////////////////////////////
}//interface
