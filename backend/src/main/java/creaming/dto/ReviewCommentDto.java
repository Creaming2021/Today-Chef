package creaming.dto;

import creaming.domain.comment.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewCommentDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        @NotEmpty
        private UUID memberId;
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
    public static class PutRequest {
        @NotEmpty
        private String content;
    }

}
