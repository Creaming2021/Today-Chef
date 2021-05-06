package creaming.controller;

import creaming.dto.*;
import creaming.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @GetMapping
//    @Operation(summary = "모든 유저 가져오기", description = "Creaming의 모든 유저의 정보를 가져옵니다.")
//    public ResponseEntity<Page<MemberDto.MemberResponse>> getMemberAll(Pageable pageable) {
//        Page<MemberDto.MemberResponse> result = memberService.getMemberAll(pageable);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @GetMapping
    @Operation(summary = "모든 유저 가져오기", description = "Creaming의 모든 유저의 정보를 가져옵니다.")
    public ResponseEntity<List<MemberDto.MemberResponse>> getMemberAll() {
        List<MemberDto.MemberResponse> result = memberService.getMemberAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "유저 정보 가져오기", description = "memberId에 해당하는 유저의 정보를 가져옵니다.")
    public ResponseEntity<MemberDto.MemberResponse> getMember(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getMember - memberId : {} ", memberId);
        MemberDto.MemberResponse result = memberService.getMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "유저 정보 수정", description = "memberId에 해당하는 유저의 정보를 수정합니다.")
    public ResponseEntity<Void> putMember(@PathVariable("memberId") Long memberId,
                                       @RequestBody MemberDto.MemberPutRequest dto) {
        log.info("(Get) putMember - memberId : {} | nickname : {} | address : {}", memberId, dto.getNickname(), dto.getAddress());
        memberService.putMember(memberId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 유저의 쿠폰 리스트
    @GetMapping("/{memberId}/coupons")
    @Operation(summary = "유저의 쿠폰 가져오기", description = "memberId 유저의 쿠폰들을 가져옵니다.")
    public ResponseEntity<List<CouponDto.CouponResponse>> getCouponAll(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getCouponAll - memberId : {} ", memberId);
        List<CouponDto.CouponResponse> result = memberService.getCouponAll(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저에게 쿠폰 발급하기
    @PostMapping("/{memberId}/coupons/{couponId}")
    @Operation(summary = "쿠폰 발급하기", description = "memberId 유저에게 couponsId의 쿠폰을 발급합니다.")
    public ResponseEntity<Void> postCoupon(@PathVariable("memberId") Long memberId,
                                          @PathVariable("couponId") Long couponId) {
        log.info("(Post) postCoupon - memberId : {} | couponId : {}", memberId, couponId);
        memberService.postCoupon(memberId, couponId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 유저의 쿠폰 사용하기
    @PutMapping("/{memberId}/coupons/{memberCouponId}")
    @Operation(summary = "쿠폰 사용하기", description = "memberId 유저의 쿠폰을 사용합니다.")
    public ResponseEntity<Void> useCoupon(@PathVariable("memberId") Long memberId,
                                       @PathVariable("memberCouponId") Long memberCouponId) {
        log.info("(Put) useCoupon - memberId : {} | memberCouponId : {}", memberId, memberCouponId);
        memberService.useCoupon(memberId, memberCouponId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 내가 수강한 강의
    @GetMapping("/{memberId}/courses/student")
    @Operation(summary = "유저가 수강한 강의 가져오기", description = "memberId 유저가 수강한 강의 리스트를 가져옵니다.")
    public ResponseEntity<List<CourseDto.CourseSimpleResponse>> getCourseStudent(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getCourseStudent - memberId : {} ", memberId);
        List<CourseDto.CourseSimpleResponse> result = memberService.getCourseStudent(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 내가 진행한 강의
    @GetMapping("/{memberId}/courses/teacher")
    @Operation(summary = "유저가 진행한 강의 가져오기", description = "memberId의 유저가 진행한 강의 리스트를 가져옵니다.")
    public ResponseEntity<List<CourseDto.CourseSimpleResponse>> getCourseTeacher(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getCourseTeacher - memberId : {}", memberId);
        List<CourseDto.CourseSimpleResponse> result = memberService.getCourseTeacher(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 결제 내역
    @GetMapping("/{memberId}/registers")
    @Operation(summary = "유저의 강의 수강 내역 가져오기", description = "memberId의 유저의 강의 수강 내역을 가져옵니다.")
    public ResponseEntity<List<RegisterDto.RegisterResponse>> getRegisterAll(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getRegisterAll - memberId : {}", memberId);
        List<RegisterDto.RegisterResponse> result = memberService.getRegisterAll(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 결제 내역 상세 보기
    @GetMapping("/{memberId}/registers/{registerId}")
    @Operation(summary = "강의 수강 내역 상세보기", description = "memberId의 registerId 강의 수강 내역을 가져옵니다.")
    public ResponseEntity<RegisterDto.RegisterResponse> getRegister(@PathVariable("memberId") Long memberId,
                                         @PathVariable("registerId") Long registerId) {
        log.info("(Get) getRegister - memberId : {} | registerId : {}", memberId, registerId);
        RegisterDto.RegisterResponse result = memberService.getRegister(memberId, registerId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 강의 수강 등록 = 결제하기
    @PostMapping("/{memberId}/registers")
    @Operation(summary = "강의 수강", description = "해당 강의를 수강 신청합니다.")
    public ResponseEntity<Long> postRegister(@PathVariable("memberId") Long memberId,
                                          @RequestBody RegisterDto.RegisterPostRequest dto) {
        log.info("(Post) postRegister - memberId : {} | courseId : {}", memberId, dto.getCourseId());
        Long result = memberService.postRegister(memberId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 강의 환불 -> 보류
    @DeleteMapping("/{memberId}/registers/{registerId}")
    @Operation(summary = "(보류) 강의 환불", description = "")
    public ResponseEntity<?> deleteRegister(@PathVariable("memberId") Long memberId,
                                            @PathVariable("registerId") Long registerId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{memberId}/courses/like")
    @Operation(summary = "좋아요한 강의 가져오기", description = "memberId가 좋아요한 강의들을 가져옵니다.")
    public ResponseEntity<List<CourseDto.CourseSimpleResponse>> getCourseLike(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getCourseLike - memberId : {}", memberId);
        List<CourseDto.CourseSimpleResponse> result = memberService.getCourseLike(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{memberId}/products/like")
    @Operation(summary = "좋아요한 키트 가져오기", description = "memberId가 좋아요한 키트들을 가져옵니다.")
    public ResponseEntity<List<ProductDto.ProductSimpleResponse>> getProductLike(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getProductLike - memberId : {}", memberId);
        List<ProductDto.ProductSimpleResponse> result = memberService.getProductLike(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 강의 좋아요 토글
    @PutMapping("/{memberId}/courses/{courseId}/like")
    @Operation(summary = "강의 좋아요 토글", description = "강의 좋아요 상태를 토글합니다.")
    public ResponseEntity<Void> toggleCourseLike(@PathVariable("memberId") Long memberId,
                                        @PathVariable("courseId") Long courseId) {
        log.info("(Put) toggleCourseLike - memberId : {}, courseId : {}", memberId, courseId);
        memberService.toggleCourseLike(memberId, courseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 상품 좋아요 토글
    @PutMapping("/{memberId}/products/{productId}/like")
    @Operation(summary = "상품좋아요 토글", description = "상품 좋아요 상태를 토글합니다.")
    public ResponseEntity<Void> toggleProductLike(@PathVariable("memberId") Long memberId,
                                           @PathVariable("productId") Long productId) {
        log.info("(Put) toggleProductLike - memberId : {}, productId : {}", memberId, productId);
        memberService.toggleProductLike(memberId, productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 장바구니 목록 불러오기
    @GetMapping("/{memberId}/carts")
    @Operation(summary = "장바구니 목록 가져오기", description = "memberId의 장바구니 목록을 가져옵니다.")
    public ResponseEntity<List<CartDto.CartResponse>> getCarts(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getCarts - memberId : {}", memberId);
        List<CartDto.CartResponse> result = memberService.getCarts(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 장바구니 담기
    @PostMapping("/{memberId}/carts")
    @Operation(summary = "장바구니에 담기", description = "memberId의 장바구니에 productId의 제품을 amount 갯수만큼 담는다.")
    public ResponseEntity<Long> postCart(@PathVariable("memberId") Long memberId,
                                                               @RequestBody @Valid CartDto.CartPostRequest dto) {
        log.info("(Post) postCart - memberId : {} | productId : {} | amount : {}", memberId, dto.getProductId(), dto.getAmount());
        Long result = memberService.postCart(memberId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 장바구니 제거
    @DeleteMapping("/{memberId}/carts/{productId}")
    @Operation(summary = "장바구니에서 제거", description = "memberId의 장바구니에 productId의 제품을 제거합니다.")
    public ResponseEntity<Void> deleteCart(@PathVariable("memberId") Long memberId,
                                                               @PathVariable("productId") Long productId) {
        log.info("(Post) postCart - memberId : {} | productId : {} ", memberId, productId);
        memberService.deleteCart(memberId, productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

