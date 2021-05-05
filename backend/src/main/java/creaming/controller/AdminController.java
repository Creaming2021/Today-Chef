package creaming.controller;

import creaming.dto.AdminDto;
import creaming.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 1. 밀키트 질문 답변
    @Operation(summary = "밀키트 질문에 대한 답변", description = "밀키트 질문 id 를 받아서 답변을 작성합니다.")
    @PutMapping("/product-qna/{productQnaId}/answer")
    public ResponseEntity<Void> postProductQna( @PathVariable("productQnaId") Long productQnaId,
            @RequestBody @Valid AdminDto.AdminProductQnaRequest dto) {
        adminService.postProductQna(productQnaId, dto.getAnswer());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 2. 쿠폰 제작
    @Operation(summary = "쿠폰 제작", description = "쿠폰을 제작합니다.")
    @PostMapping("/coupons")
    public ResponseEntity<Long> postCoupon(@RequestBody @Valid AdminDto.AdminCouponRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.postCoupon(dto));
    }

}
