package creaming.dto;

import creaming.domain.comment.QnaComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public class QnaCommentDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        @NotEmpty
        private Long memberId;
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
    public static class PutRequest {
        private String content;
    }

}
