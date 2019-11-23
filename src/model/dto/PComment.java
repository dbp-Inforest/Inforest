package model.dto;

import java.sql.Date;

public class PComment extends Product{

	private String commentId;
	private double recommandation;
	private String content;
	private String userId;
	private String postId;
	private Date registDate;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public double getRecommandation() {
		return recommandation;
	}
	public void setRecommandation(double recommandation) {
		this.recommandation = recommandation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	
}
