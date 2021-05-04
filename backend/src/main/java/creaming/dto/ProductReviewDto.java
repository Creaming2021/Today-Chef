package creaming.dto;

import creaming.domain.review.ProductReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ProductReviewDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductReviewResponse {
        private Long productReviewId;
        private MemberDto.MemberSimpleProfile profile;
        private String title;
        private String content;
        private LocalDateTime date;
        private Integer rating;

        public ProductReviewResponse(ProductReview productReview) {
            this.productReviewId = productReview.getId();
            this.profile = new MemberDto.MemberSimpleProfile(productReview.getMember());
            this.title = productReview.getTitle();
            this.content = productReview.getContent();
            this.date = productReview.getCreatedDate();
            this.rating = productReview.getRating();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductReviewPostRequest {
        private Long memberId;
        private Long productId;
        private String title;
        private String content;
        private Integer rating;

        public ProductReview toEntity() {
            return ProductReview.builder()
                    .title(title)
                    .content(content)
                    .rating(rating)
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductReviewPutRequest {
        private String title;
        private String content;
        private Integer rating;
    }
}
