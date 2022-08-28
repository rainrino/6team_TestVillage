package dao;

import java.util.List;
import java.util.Map;

import controller.Comment;

public interface CommentDAO {

	public List<Comment> selectCommentListTest1(); //db에서 댓글을 가져오는 메소드(최근2개만)
	int insertCommentTest1(Comment c); //테스트1 댓글창에 댓글 입력
	public List<Comment> selectAllCommentListTest1(); //모든 댓글 가져오는 메소드
	
	public List<Comment> selectCommentListTest2(); //db에서 댓글을 가져오는 메소드(최근2개만)
	int insertCommentTest2(Comment c); //테스트2 댓글창에 댓글 입력
	public List<Comment> selectAllCommentListTest2(); //모든 댓글 가져오는 메소드
	
	public List<Comment> selectCommentListTest3(); //db에서 댓글을 가져오는 메소드(최근2개만)
	int insertCommentTest3(Comment c); //테스트3 댓글창에 댓글 입력
	public List<Comment> selectAllCommentListTest3(); //모든 댓글 가져오는 메소드
}
