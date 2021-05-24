package creaming.dto;

import creaming.domain.course.Course;
import creaming.domain.etc.FoodType;
import creaming.domain.file.ProductFile;
import creaming.domain.product.Product;
import creaming.domain.review.ProductReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductSimpleResponse {
        private Long productId;
        private String name;
        private Integer price;
        private FoodType category;
        private Double rating;
        private String image;
        private Integer reviewCnt;

        public ProductSimpleResponse(Product product) {
            this.productId = product.getId();
            this.name = product.getName();
            this.price = product.getPrice();
            this.category = product.getCategory();
            List<ProductFile> productFiles = product.getProductFiles();
            if (!productFiles.isEmpty()) {
                this.image = productFiles.get(0).getFileName();
            }
            this.rating = product.getProductReviews().stream()
                    .collect(Collectors.averagingDouble(ProductReview::getRating));
            this.reviewCnt = product.getProductReviews().size();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ProductDetailResponse {
        private Long productId;
        private String name;
        private Integer price;
        private FoodType category;
        private Double rating;
        private String descriptions;
        private List<ImageDto> images;
        private List<Long> courses;
        private Integer like;

        public ProductDetailResponse(Product product) {
            this.productId = product.getId();
            this.name = product.getName();
            this.price = product.getPrice();
            this.category = product.getCategory();
            this.descriptions = product.getDescription();
            this.courses = product.getCourses().stream()
                    .map(Course::getId).collect(Collectors.toList());
            this.images = product.getProductFiles().stream()
                    .map(productFile -> new ImageDto(productFile.getId(), productFile.getFileName()))
                    .collect(Collectors.toList());
            this.rating = product.getProductReviews().stream()
                    .collect(Collectors.averagingDouble(ProductReview::getRating));
            this.like = product.getProductLikes().size();
        }
    }

    @Getter
    public static class ProductRequest {
        @NotEmpty
        private String name;
        @NotNull
        private Integer price;
        @NotNull
        private FoodType category;
        @NotEmpty
        private String descriptions;

        public Product toEntity() {
            return Product.builder()
                    .name(this.name)
                    .price(this.price)
                    .category(this.category)
                    .description(this.descriptions)
                    .build();
        }
    }
}
