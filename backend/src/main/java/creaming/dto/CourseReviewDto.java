package creaming.dto;

import creaming.domain.comment.ReviewComment;
import creaming.domain.review.CourseReview;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CourseReviewDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseReviewSimpleResponse {
        private Long courseReviewId;
        private MemberDto.MemberSimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private int commentCount;

        public CourseReviewSimpleResponse(CourseReview courseReview) {
            this.courseReviewId = courseReview.getId();
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
    public static class CourseReviewDetailResponse {
        private Long courseReviewId;
        private MemberDto.MemberSimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime date;
        private int commentCount;
        private List<CourseReviewComment> comments;

        public CourseReviewDetailResponse(CourseReview courseReview) {
            this.courseReviewId = courseReview.getId();
            this.profile = new MemberDto.MemberSimpleProfile(courseReview.getMember());
            this.rating = courseReview.getRating();
            this.title = courseReview.getTitle();
            this.content = courseReview.getContent();
            this.date = courseReview.getLastModifiedDate();
            this.commentCount = courseReview.getReviewComments().size();
            this.comments = courseReview.getReviewComments().stream()
                    .map(CourseReviewComment::new).collect(Collectors.toList());
        }

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseReviewComment {
        private Long commentId;
        private MemberDto.MemberSimpleProfile profile;
        private String content;
        private LocalDateTime date;

        public CourseReviewComment(ReviewComment reviewComment) {
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
    public static class CourseReviewPostRequest {
        @NotNull
        private Long memberId;
        @NotNull
        private Long courseId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotNull
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
    public static class CourseReviewPutRequest {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotEmpty
        private String rating;
    }

}
