package com.mongospring.mongowithspring.model.builders;

public class Review {

    private String commentTitle;
    private String commentDescription;

    private Review() {
        //Empty Constructor
    }

    public Review(ReviewBuilder reviewBuilder) {
        super();
        this.commentTitle = reviewBuilder.commentTitle;
        this.commentDescription = reviewBuilder.commentDescription;
    }
	public static ReviewBuilder builder() {
		return new ReviewBuilder();
	}
    public String getCommentTitle() {
        return commentTitle;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    @Override
    public String toString() {
        return "Review [commentTitle=" + commentTitle + ", commentDescription=" + commentDescription + "]";
    }

    public static class ReviewBuilder {
        private String commentTitle;
        private String commentDescription;

        private ReviewBuilder() {
            //Private Constructor
        }

        public ReviewBuilder commentTitle(String title) {
            this.commentTitle = title;
            return this;
        }

        public ReviewBuilder commentDescription(String desc) {
            this.commentDescription = desc;
            return this;
        }

        public Review build() {
            return new Review(this);
        }

    }
}
