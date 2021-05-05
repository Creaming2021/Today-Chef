package creaming.controller;

import creaming.dto.ProductQnaDto;
import creaming.service.ProductQnaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("product-qna")
@RequiredArgsConstructor
public class ProductQnaController {

    private final ProductQnaService productQnaService;

    @Operation(summary = "Product QnA 리스트", description = "Product QnA 리스트를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<ProductQnaDto.ProductQnaResponse>> getProductQnaAll(@RequestParam("productId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productQnaService.getProductQnaList(id));
    }

    @Operation(summary = "Product QnA 작성", description = "Product QnA를 작성합니다.")
    @PostMapping
    public ResponseEntity<Long> postProductQna(@RequestBody ProductQnaDto.ProductQnaPostRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productQnaService.postProductQna(dto));
    }

    @Operation(summary = "Product QnA 수정", description = "Product QnA를 수정합니다.")
    @PutMapping("/{productQnaId}")
    public ResponseEntity<Long> putProductQna(@PathVariable("productQnaId")Long id,
                                              @RequestBody ProductQnaDto.ProductQnaPutRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productQnaService.putProductQna(id, dto));
    }

    @Operation(summary = "Product QnA 삭제", description = "Product QnA를 삭제합니다.")
    @DeleteMapping("/{productQnaId}")
    public ResponseEntity<Void> deleteProductQna(@PathVariable("productQnaId") Long id) {
        productQnaService.deleteProductQna(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
