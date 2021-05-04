package creaming.dto;

import creaming.domain.qna.ProductQna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class ProductQnaDto {

    @Getter
    public static class ProductQnaPostRequest {
        @NotEmpty
        private Long productId;
        @NotEmpty
        private Long userId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotEmpty
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
    public static class ProductQnaPutRequest {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotEmpty
        private Boolean isSecret;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductQnaResponse {
        private Long productQnaId;
        private MemberDto.MemberSimpleProfile profile;
        private String title;
        private LocalDateTime date;
        private String content;
        private Boolean isSecret;
        private String answer;
        private LocalDateTime answerDate;

        public ProductQnaResponse(ProductQna productQna) {
            this.productQnaId = productQna.getId();
            this.profile = new MemberDto.MemberSimpleProfile(productQna.getMember());
            this.title = productQna.getTitle();
            this.date = productQna.getCreatedDate();
            this.content = productQna.getContent();
            this.isSecret = productQna.getIsSecret();
            this.answer = productQna.getAnswer();
            this.answerDate = productQna.getAnswerDate();
        }
    }
}
