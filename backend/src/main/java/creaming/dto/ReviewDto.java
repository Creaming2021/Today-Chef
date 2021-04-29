package creaming.dto;

import creaming.domain.comment.ReviewComment;
import creaming.domain.review.Review;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private Long reviewId;
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
        private Long reviewId;
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
        private Long commentId;
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
        @NotEmpty
        private Long memberId;
        @NotEmpty
        private Long courseId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotEmpty
        private Integer rating;

        public Review toEntity() {
            return Review.builder()
                    .title(this.title)
                    .content(this.content)
                    .rating(this.rating)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        @NotEmpty
        private String content;
    }

}
