package creaming.dto;

import creaming.domain.comment.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewCommentDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCommentPostRequest {
        @NotNull
        private Long memberId;
        @NotEmpty
        private String content;

        public ReviewComment toEntity() {
            return ReviewComment.builder()
                    .content(this.content)
                    .build();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCommentPutRequest {
        @NotEmpty
        private String content;
    }

}
