package creaming.dto;

import creaming.domain.comment.QnaComment;
import creaming.domain.qna.Qna;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class QnaDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private UUID qnaId;
        private MemberDto.SimpleProfile profile;
        private String title;
        private LocalDateTime date;
        private String content;
        private boolean isSecret;
        private List<Comment> comments;

        public Response(Qna qna) {
            this.qnaId = qna.getId();
            this.profile = new MemberDto.SimpleProfile(qna.getMember());
            this.title = qna.getTitle();
            this.date = qna.getLastModifiedDate();
            this.content = qna.getContent();
            this.isSecret = qna.getIsSecret();
            this.comments = qna.getQnaComments().stream()
                    .map(Comment::new).collect(Collectors.toList());
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {

        @NotEmpty
        private UUID memberId;
        @NotEmpty
        private UUID courseId;
        @NotEmpty
        private String title;
        private String content; // TODO editor 로 작업시 수정
        private boolean isSecret;

        public Qna toEntity() {
            return Qna.builder()
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
    public static class PutRequest {
        private String title;
        private String content;
        private boolean isSecret;
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

        public Comment(QnaComment qnaComment) {
            this.commentId = qnaComment.getId();
            this.profile = new MemberDto.SimpleProfile(qnaComment.getMember());
            this.content = qnaComment.getContent();
            this.date = qnaComment.getLastModifiedDate();
        }
    }
}
