package creaming.controller;

import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.dto.RegisterDto;
import creaming.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @Operation(summary = "모든 유저 가져오기", description = "Creaming의 모든 유저의 정보를 가져옵니다.")
    public ResponseEntity<Page<MemberDto.Response>> getMemberAll(Pageable pageable) {
        Page<MemberDto.Response> result = memberService.getMemberAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "유저 정보 가져오기", description = "memberId에 해당하는 유저의 정보를 가져옵니다.")
    public ResponseEntity<MemberDto.Response> getMember(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getMember - memberId : {} ", memberId);
        MemberDto.Response result = memberService.getMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "유저 정보 수정", description = "memberId에 해당하는 유저의 정보를 수정합니다.")
    public ResponseEntity<Void> putMember(@PathVariable("memberId") Long memberId,
                                       @RequestBody MemberDto.PutRequest dto) {
        log.info("(Get) putMember - memberId : {} | nickname : {} | address : {}", memberId, dto.getNickname(), dto.getAddress());
        memberService.putMember(memberId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PostMapping("/{memberId}/image")
//    @Operation(summary = "회원 이미지", description = "")
//    public ResponseEntity<?> postMemberImage(@PathVariable("memberId") Long memberId,
//                                             @RequestPart MultipartFile file) {
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    // 유저의 쿠폰 리스트
    @GetMapping("/{memberId}/coupons")
    @Operation(summary = "유저의 쿠폰 가져오기", description = "memberId 유저의 쿠폰들을 가져옵니다.")
    public ResponseEntity<List<MemberDto.Response>> getCouponAll(@PathVariable("memberId") Long memberId) {
//        log.info("(Get) getCouponAll - memberId : {} ", memberId);
        List<MemberDto.Response> result = memberService.getCouponAll(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{memberId}/coupons/{couponId}")
    @Operation(summary = "쿠폰 사용하기", description = "memberId 유저의 쿠폰을 사용합니다.")
    public ResponseEntity<Void> useCoupon(@PathVariable("memberId") Long memberId,
                                       @PathVariable("couponId") Long couponId) {
//        log.info("(Put) useCoupon - memberId : {} | couponId : {}", memberId, couponId);
        memberService.useCoupon(memberId, couponId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 내가 수강한 강의
    @GetMapping("/{memberId}/courses/student")
    @Operation(summary = "유저가 수강한 강의 가져오기", description = "memberId 유저가 수강한 강의 리스트를 가져옵니다.")
    public ResponseEntity<List<CourseDto.SimpleResponse>> getCourseStudent(@PathVariable("memberId") Long memberId) {
//        log.info("(Get) getCourseStudent - memberId : {} ", memberId);
        List<CourseDto.SimpleResponse> result = memberService.getCourseStudent(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 내가 진행한 강의
    @GetMapping("/{memberId}/courses/teacher")
    @Operation(summary = "유저가 진행한 강의 가져오기", description = "memberId의 유저가 진행한 강의 리스트를 가져옵니다.")
    public ResponseEntity<List<CourseDto.SimpleResponse>> getCourseTeacher(@PathVariable("memberId") Long memberId) {
        log.info("(Get) getCourseTeacher - memberId : {}", memberId);
        List<CourseDto.SimpleResponse> result = memberService.getCourseTeacher(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 결제 내역
    @GetMapping("/{memberId}/registers")
    @Operation(summary = "유저의 결제 내역 가져오기", description = "memberId의 유저의 결제 내역을 가져옵니다.")
    public ResponseEntity<List<RegisterDto.SimpleResponse>> getRegisterAll(@PathVariable("memberId") Long memberId) {
        List<RegisterDto.SimpleResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 결제 내역 상세 보기
    @GetMapping("/{memberId}/registers/{registerId}")
    @Operation(summary = "결제 내역 상세보기", description = "memberId의 registerId 결제 내역을 가져옵니다.")
    public ResponseEntity<?> getRegister(@PathVariable("memberId") Long memberId,
                                         @PathVariable("registerId") Long registerId) {
        RegisterDto.DetailResponse result = new RegisterDto.DetailResponse();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 강의 수강 등록 = 결제하기
    @PostMapping("/{memberId}/registers")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> postRegister(@PathVariable("memberId") Long memberId,
                                          @RequestBody RegisterDto.PostRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(1L);
    }

    // 강의 환불 -> 보류
    @DeleteMapping("/{memberId}/registers/{registerId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> deleteRegister(@PathVariable("memberId") Long memberId,
                                            @PathVariable("registerId") Long registerId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 좋아요 토글
    @PutMapping("/{memberId}/courses/{courseId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<?> toggleLike(@PathVariable("memberId") Long memberId,
                                        @PathVariable("courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

