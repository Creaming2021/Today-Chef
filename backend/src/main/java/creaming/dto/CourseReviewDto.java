package creaming.dto;

import creaming.domain.comment.ReviewComment;
import creaming.domain.review.CourseReview;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CourseReviewDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private Long reviewId;
        private MemberDto.MemberSimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private int commentCount;

        public SimpleResponse(CourseReview courseReview) {
            this.reviewId = courseReview.getId();
            this.profile = new MemberDto.MemberSimpleProfile(courseReview.getMember());
            this.rating = courseReview.getRating();
            this.title = courseReview.getTitle();
            this.content = courseReview.getContent();
            this.createdDate = courseReview.getCreatedDate();
            this.commentCount = courseReview.getReviewComments().size();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse {
        private Long reviewId;
        private MemberDto.MemberSimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime date;
        private int commentCount;
        private List<Comment> comments;

        public DetailResponse(CourseReview courseReview) {
            this.reviewId = courseReview.getId();
            this.profile = new MemberDto.MemberSimpleProfile(courseReview.getMember());
            this.rating = courseReview.getRating();
            this.title = courseReview.getTitle();
            this.content = courseReview.getContent();
            this.date = courseReview.getLastModifiedDate();
            this.commentCount = courseReview.getReviewComments().size();
            this.comments = courseReview.getReviewComments().stream()
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
        private MemberDto.MemberSimpleProfile profile;
        private String content;
        private LocalDateTime date;

        public Comment(ReviewComment reviewComment) {
            this.commentId = reviewComment.getId();
            this.profile = new MemberDto.MemberSimpleProfile(reviewComment.getMember());
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

        public CourseReview toEntity() {
            return CourseReview.builder()
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
        private String title;
        @NotEmpty
        private String content;
        @NotEmpty
        private String rating;
    }

}
