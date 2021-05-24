package creaming.dto;

import creaming.domain.review.ProductReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
        private LocalDateTime createdDate;
        private Integer rating;

        public ProductReviewResponse(ProductReview productReview) {
            this.productReviewId = productReview.getId();
            this.profile = new MemberDto.MemberSimpleProfile(productReview.getMember());
            this.title = productReview.getTitle();
            this.content = productReview.getContent();
            this.createdDate = productReview.getCreatedDate();
            this.rating = productReview.getRating();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductReviewPostRequest {

        @NotNull
        private Long memberId;
        @NotNull
        private Long productId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotNull
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
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotNull
        private Integer rating;
    }
}
