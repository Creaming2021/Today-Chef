package creaming.dto;

import creaming.domain.comment.QnaComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class QnaCommentDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QnaCommentPostRequest {
        @NotNull
        private Long memberId;
        @NotEmpty
        private String content;

        public QnaComment toEntity() {
            return QnaComment.builder()
                    .content(content)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QnaCommentPutRequest {
        @NotEmpty
        private String content;
    }

}
