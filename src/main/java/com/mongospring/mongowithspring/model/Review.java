package com.mongospring.mongowithspring.model;

public class Review {
	
	private String commentTitle;
	private String commentDescription;
	
	public Review() {
		//Empty Constructor
	}
	public Review(String commentTitle, String commentDescription) {
		super();
		this.commentTitle = commentTitle;
		this.commentDescription = commentDescription;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	public String getCommentDescription() {
		return commentDescription;
	}
	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}
	@Override
	public String toString() {
		return "Review [commentTitle=" + commentTitle + ", commentDescription=" + commentDescription + "]";
	}
	
	
	

}
