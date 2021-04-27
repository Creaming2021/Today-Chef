package creaming.dto;

import creaming.domain.comment.ReviewComment;
import creaming.domain.review.Review;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReviewDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private UUID reviewId;
        private MemberDto.SimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private int commentCount;

        public SimpleResponse(Review review) {
            this.reviewId = review.getId();
            this.profile = new MemberDto.SimpleProfile(review.getMember());
            this.rating = review.getRating();
            this.title = review.getTitle();
            this.content = review.getContent();
            this.createdDate = review.getCreatedDate();
            this.commentCount = review.getReviewComments().size();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse {
        private UUID reviewId;
        private MemberDto.SimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime date;
        private int commentCount;
        private List<Comment> comments;

        public DetailResponse(Review review) {
            this.reviewId = review.getId();
            this.profile = new MemberDto.SimpleProfile(review.getMember());
            this.rating = review.getRating();
            this.title = review.getTitle();
            this.content = review.getContent();
            this.date = review.getLastModifiedDate();
            this.commentCount = review.getReviewComments().size();
            this.comments = review.getReviewComments().stream()
                    .map(Comment::new).collect(Collectors.toList());
        }

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private UUID commentId;
        private MemberDto.SimpleProfile profile;
        private String content;
        private LocalDateTime date;

        public Comment(ReviewComment reviewComment) {
            this.commentId = reviewComment.getId();
            this.profile = new MemberDto.SimpleProfile(reviewComment.getMember());
            this.content = reviewComment.getContent();
            this.date = reviewComment.getLastModifiedDate();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private UUID memberId;
        private UUID courseId;
        private String title;
        private String content;
        private Integer rating;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        private String content;
    }

}
