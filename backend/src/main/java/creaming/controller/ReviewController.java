package creaming.controller;

import creaming.dto.ReviewCommentDto;
import creaming.dto.ReviewDto;
import creaming.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "강의 별 모든 리뷰 조회", description = "courseId에 해당하는 모든 리뷰를 페이징 처리해서 가져옵니다.")
    public ResponseEntity<?> getReviewAll(@RequestParam("courseId")UUID courseId,
                                          Pageable pageable) {
        Page<ReviewDto.SimpleResponse> result = reviewService.getReviewAll(courseId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable("reviewId") UUID reviewId) {
        ReviewDto.DetailResponse result = reviewService.getReview(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody ReviewDto.PostRequest dto) {
        UUID result = reviewService.postReview(dto);
        log.info("");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> putReview(@PathVariable("reviewId") UUID reviewId,
                                       @RequestBody ReviewDto.PutRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{reviewId}/comments")
    public ResponseEntity<?> postReviewComment(@PathVariable("reviewId") UUID reviewId,
                                               @RequestBody ReviewCommentDto.PostRequest dto) {
        UUID result = UUID.randomUUID();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{reviewId}/comments/{commentId}")
    public ResponseEntity<?> putReviewComment(@PathVariable("reviewId") UUID reviewId,
                                              @PathVariable("commentId") UUID commentId,
                                              @RequestBody ReviewCommentDto.PutRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{reviewId}/comments/{commentId}")
    public ResponseEntity<?> deleteReviewComment(@PathVariable("reviewId") UUID reviewId,
                                                 @PathVariable("commentId") UUID commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
