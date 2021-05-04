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
    public static class Response {
        private Long productReviewId;
        private MemberDto.SimpleProfile profile;
        private String title;
        private String content;
        private LocalDateTime date;
        private Integer rating;

        public Response(ProductReview productReview) {
            this.productReviewId = productReview.getId();
            this.profile = new MemberDto.SimpleProfile(productReview.getMember());
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
    public static class PostRequest {
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
    public static class PutRequest {
        private String title;
        private String content;
        private Integer rating;
    }
}
