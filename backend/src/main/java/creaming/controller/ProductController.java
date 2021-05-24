package creaming.controller;

import creaming.domain.etc.FoodType;
import creaming.dto.ProductDto;
import creaming.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "상품 리스트", description = "상품 리스트를 조회합니다.")
    public ResponseEntity<List<ProductDto.ProductSimpleResponse>> getProductList() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductList());
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 상세 조회", description = "상품을 상세 조회합니다.")
    public ResponseEntity<ProductDto.ProductDetailResponse> getProduct(@PathVariable("productId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetail(id));
    }

    @PostMapping
    @Operation(summary = "상품 등록", description = "상품을 등록합니다.")
    public ResponseEntity<Long> postProduct(@RequestBody ProductDto.ProductRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.postProduct(dto));
    }

    @PutMapping("/{productId}")
    @Operation(summary = "상품 수정", description = "상품을 수정합니다.")
    public ResponseEntity<Long> putProduct(@PathVariable("productId") Long id,
                                           @RequestBody ProductDto.ProductRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.putProduct(id, dto));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "상품 삭제", description = "상품을 삭제합니다.")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "카테고리별 top (count) 제품 리스트 요청", description = "카테고리별 상위권 제품을 count 개수만큼 가져옵니다.")
    @GetMapping("/rank")
    public ResponseEntity<List<ProductDto.ProductSimpleResponse>> getProductRankByRating(@RequestParam("count") int count,
                                                                                      @RequestParam("category") FoodType category)  {
        log.info("(Get) getProductRankByRating - count : {} | category : {}", count, category);
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductRankByRating(count, category));
    }

}
