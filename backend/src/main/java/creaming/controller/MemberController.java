package creaming.controller;

import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.dto.QnaDto;
import creaming.dto.RegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("members")
public class MemberController {

    @GetMapping
    public ResponseEntity<?> getMemberAll(Pageable pageable) {
        List<MemberDto.Response> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable("memberId") UUID memberId) {
        MemberDto.Response result = new MemberDto.Response();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> putMember(@PathVariable("memberId") UUID memberId,
                                       @RequestBody MemberDto.PutRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{memberId}/image")
    public ResponseEntity<?> postMemberImage(@PathVariable("memberId") UUID memberId,
                                             @RequestPart MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 유저의 쿠폰 리스트
    @GetMapping("/{memberId}/coupons")
    public ResponseEntity<?> getCouponAll(@PathVariable("memberId") UUID memberId) {
        List<MemberDto.Response> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 유저가 가진 쿠폰 한 개 사용 => GET 이 맞을까 PUT 이 맞을까
    @PutMapping("/{memberId}/coupons/{couponId}")
    public ResponseEntity<?> useCoupon(@PathVariable("memberId") UUID memberId,
                                       @PathVariable("couponId") UUID couponId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 내가 수강한 강의
    @GetMapping("/{memberId}/courses/student")
    public ResponseEntity<?> getCourseStudent(@PathVariable("memberId") UUID memberId) {
        List<CourseDto.SimpleResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 내가 진행한 강의
    @GetMapping("/{memberId}/courses/teacher")
    public ResponseEntity<?> getCourseTeacher(@PathVariable("memberId") UUID memberId) {
        List<CourseDto.SimpleResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 결제 내역
    @GetMapping("/{memberId}/registers")
    public ResponseEntity<?> getRegisterAll(@PathVariable("memberId") UUID memberId) {
        List<RegisterDto.SimpleResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 결제 내역 상세 보기
    @GetMapping("/{memberId}/registers/{registerId}")
    public ResponseEntity<?> getRegister(@PathVariable("memberId") UUID memberId,
                                         @PathVariable("registerId") UUID registerId) {
        RegisterDto.DetailResponse result = new RegisterDto.DetailResponse();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 강의 수강 등록 = 결제하기
    @PostMapping("/{memberId}/registers")
    public ResponseEntity<?> postRegister(@PathVariable("memberId") UUID memberId,
                                          @RequestBody RegisterDto.PostRequest dto) {
        UUID result  = UUID.randomUUID();
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 강의 환불 -> 보류
    @DeleteMapping("/{memberId}/registers/{registerId}")
    public ResponseEntity<?> deleteRegister(@PathVariable("memberId") UUID memberId,
                                            @PathVariable("registerId") UUID registerId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 좋아요 토글
    @PutMapping("/{memberId}/courses/{courseId}")
    public ResponseEntity<?> toggleLike(@PathVariable("memberId") UUID memberId,
                                        @PathVariable("courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}

