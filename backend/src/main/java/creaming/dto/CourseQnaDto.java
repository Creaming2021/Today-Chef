package creaming.dto;

import creaming.domain.comment.QnaComment;
import creaming.domain.qna.CourseQna;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CourseQnaDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseQnaResponse {
        private Long qnaId;
        private MemberDto.MemberSimpleProfile profile;
        private String title;
        private LocalDateTime date;
        private String content;
        private boolean isSecret;
        private List<CourseQnaComment> comments;

        public CourseQnaResponse(CourseQna courseQna) {
            this.qnaId = courseQna.getId();
            this.profile = new MemberDto.MemberSimpleProfile(courseQna.getMember());
            this.title = courseQna.getTitle();
            this.date = courseQna.getLastModifiedDate();
            this.content = courseQna.getContent();
            this.isSecret = courseQna.getIsSecret();
            this.comments = courseQna.getQnaComments().stream()
                    .map(CourseQnaComment::new).collect(Collectors.toList());
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseQnaPostRequest {

        @NotEmpty
        private Long memberId;
        @NotEmpty
        private Long courseId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content; // TODO editor 로 작업시 수정
        @NotEmpty
        private boolean isSecret;

        public CourseQna toEntity() {
            return CourseQna.builder()
                    .title(this.title)
                    .content(this.content)
                    .isSecret(this.isSecret)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseQnaPutRequest {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotEmpty
        private boolean isSecret;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseQnaComment {
        private Long commentId;
        private MemberDto.MemberSimpleProfile profile;
        private String content;
        private LocalDateTime date;

        public CourseQnaComment(QnaComment qnaComment) {
            this.commentId = qnaComment.getId();
            this.profile = new MemberDto.MemberSimpleProfile(qnaComment.getMember());
            this.content = qnaComment.getContent();
            this.date = qnaComment.getLastModifiedDate();
        }
    }
}
