package creaming.controller;

import creaming.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // member 프로필 사진
    @PostMapping("/members/{memberId}")
    @Operation(summary = "회원 이미지 삽입 & 수정", description = "회원 이미지를 저장 & 수정합니다.")
    public ResponseEntity<Void> postMemberImage(@PathVariable("memberId") Long memberId,
                                             @RequestPart MultipartFile file) throws IOException {
        log.info("(Post) postMemberImage - memberId : {} ", memberId);
        imageService.postMemberImage(memberId, file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // product 사진
    @PostMapping("/products/{productId}")
    @Operation(summary = "상품 이미지 삽입 & 수정", description = "상품 이미지를 저장 & 수정합니다.")
    public ResponseEntity<Void> postProductFile(@PathVariable("productId") Long productId,
                                                 @RequestPart MultipartFile file) throws IOException {
        log.info("(Post) postProductImage - productId : {} ", productId);
        imageService.postProductFile(productId, file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // product 사진 삭제 - fileId 필요 => [ fileId = imageId ]
    @DeleteMapping("/products/{productId}/{productImageId}")
    @Operation(summary = "상품 이미지 삭제", description = "상품 이미지를 삭제합니다.")
    public ResponseEntity<Void> deleteProductFile(@PathVariable("productId") Long productId,
                                                  @PathVariable("productImageId") Long productImageId) {
        log.info("(Delete) deleteProductFile - productId : {} | productImageId : {}", productId, productImageId);
        imageService.deleteProductFile(productId, productImageId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // course 사진
    @PostMapping("/courses/{courseId}")
    @Operation(summary = "강의 이미지 삽입 & 수정", description = "강의 이미지를 저장 & 수정합니다.")
    public ResponseEntity<Void> postCourseFile(@PathVariable("courseId") Long courseId,
                                                @RequestPart MultipartFile file) throws IOException {
        log.info("(Post) postCourseFile - courseId : {} ", courseId);
        imageService.postCourseFile(courseId, file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // course 사진 삭제
    @DeleteMapping("/courses/{courseId}/{courseImageId}")
    @Operation(summary = "강의 이미지 삭제", description = "강의 이미지를 삭제합니다.")
    public ResponseEntity<Void> deleteCourseFile(@PathVariable("courseId") Long courseId,
                                                  @PathVariable("courseImageId") Long courseImageId) {
        log.info("(Delete) deleteCourseFile - courseId : {} | courseImageId : {}", courseId, courseImageId);
        imageService.deleteCourseFile(courseId, courseImageId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // event 사진
    @PostMapping("/events/{eventId}")
    @Operation(summary = "이벤트 이미지 삽입 & 수정", description = "이벤트 이미지를 저장 & 수정합니다.")
    public ResponseEntity<Void> postEventImage(@PathVariable("eventId") Long eventId,
                                                     @RequestPart MultipartFile file) throws IOException {
        log.info("(Post) postEventImage - eventId : {} ", eventId);
        imageService.postEventImage(eventId, file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
