package creaming.controller;

import creaming.common.Validator;
import creaming.dto.ProductReviewDto;
import creaming.service.ProductReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("product-reviews")
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReviewService;
    private final Validator validator;

    @Operation(summary = "상품 리뷰 전체 조회", description = "상품 전체를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<ProductReviewDto.ProductReviewResponse>> getProductReviewList(@RequestParam("productId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productReviewService.getProductReviewList(id));
    }

    @Operation(summary = "상품 리뷰 등록", description = "상품 리뷰를 등록합니다.")
    @PostMapping
    public ResponseEntity<Long> postProductReview(@RequestBody @Valid ProductReviewDto.ProductReviewPostRequest dto) {
        validator.ratingValidator(dto.getRating());
        return ResponseEntity.status(HttpStatus.OK).body(productReviewService.postProductReview(dto));
    }

    @Operation(summary = "상품 리뷰 수정", description = "상품 리뷰를 수정합니다.")
    @PutMapping("/{productReviewId}")
    public ResponseEntity<Long> putProductReview(@PathVariable("productReviewId") Long id,
                                                 @RequestBody @Valid ProductReviewDto.ProductReviewPutRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productReviewService.putProductReview(id, dto));
    }

    @Operation(summary = "상품 리뷰 삭제", description = "상품 리뷰를 삭제합니다.")
    @DeleteMapping("/{productReviewId}")
    public ResponseEntity<Void> deleteProductReview(@PathVariable("productReviewId") Long id) {
        productReviewService.deleteProductReview(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
