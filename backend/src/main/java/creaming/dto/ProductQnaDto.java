package creaming.dto;

import creaming.domain.qna.ProductQna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ProductQnaDto {

    @Getter
    public static class PostRequest {
        private Long productId;
        private Long userId;
        private String title;
        private String content;
        private Boolean isSecret;

        public ProductQna toEntity() {
            return ProductQna.builder()
                    .title(this.title)
                    .content(this.content)
                    .isSecret(this.isSecret)
                    .build();
        }
    }

    @Getter
    public static class PutRequest {
        private String title;
        private String content;
        private Boolean isSecret;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long productQnaId;
        private MemberDto.SimpleProfile profile;
        private String title;
        private LocalDateTime date;
        private String content;
        private Boolean isSecret;
        private String answer;
        private LocalDateTime answerDate;

        public Response(ProductQna productQna) {
            this.productQnaId = productQna.getId();
            this.profile = new MemberDto.SimpleProfile(productQna.getMember());
            this.title = productQna.getTitle();
            this.date = productQna.getCreatedDate();
            this.content = productQna.getContent();
            this.isSecret = productQna.getIsSecret();
            this.answer = productQna.getAnswer();
            this.answerDate = productQna.getAnswerDate();
        }
    }
}
