package creaming.controller;

import creaming.common.Validator;
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

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final Validator validator;

    @GetMapping
    @Operation(summary = "강의 별 모든 리뷰 조회", description = "courseId에 해당하는 모든 리뷰를 페이징 처리해서 가져옵니다.")
    public ResponseEntity<Page<ReviewDto.SimpleResponse>> getReviewAll(@RequestParam("courseId")Long courseId, Pageable pageable) {
        log.info("(Get) getReviewAll - courseId : {}", courseId);
        Page<ReviewDto.SimpleResponse> result = reviewService.getReviewAll(courseId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "상세 리뷰 조회", description = "reviewId에 해당하는 리뷰 디테일을 가져옵니다.")
    public ResponseEntity<ReviewDto.DetailResponse> getReview(@PathVariable("reviewId") Long reviewId) {
        log.info("(Get) getReview - reviewId : {}", reviewId);
        ReviewDto.DetailResponse result = reviewService.getReview(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "memberId의 사용자가 courseId에 해당하는 강의에 리뷰를 작성합니다.")
    public ResponseEntity<Long> postReview(@RequestBody @Valid ReviewDto.PostRequest dto) {
        log.info("(Post) postReview - title : {} | content : {} | rating : {}", dto.getTitle(), dto.getContent(), dto.getRating());
        validator.ratingValidator(dto.getRating());
        Long result = reviewService.postReview(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "리뷰 수정", description = "작성자 memberId가 reviewId에 해당하는 리뷰를 수정합니다.")
    public ResponseEntity<Void> putReview(@PathVariable("reviewId") Long reviewId,
                                       @RequestBody @Valid ReviewDto.PutRequest dto) {
        log.info("(Put) putReview - content : {}", dto.getContent());
        reviewService.putReview(reviewId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{reviewId}/comments")
    @Operation(summary = "리뷰 댓글 작성", description = "작성자 memberId가 reviewId에 해당하는 리뷰를 수정합니다.")
    public ResponseEntity<Long> postReviewComment(@PathVariable("reviewId") Long reviewId,
                                               @RequestBody @Valid ReviewCommentDto.PostRequest dto) {
        log.info("(Post) postReviewComment - memberId : {} | content : {}", dto.getMemberId() , dto.getContent());
        Long result = reviewService.postReviewComment(reviewId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{reviewId}/comments/{commentId}")
    public ResponseEntity<Void> putReviewComment(@PathVariable("reviewId") Long reviewId,
                                              @PathVariable("commentId") Long commentId,
                                              @RequestBody @Valid ReviewCommentDto.PutRequest dto) {
        log.info("(Put) putReviewComment - reviewId : {} | commentId : {} | content : {}", reviewId, commentId, dto);
        reviewService.putReviewComment(reviewId, commentId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{reviewId}/comments/{commentId}")
    public ResponseEntity<Void> deleteReviewComment(@PathVariable("reviewId") Long reviewId,
                                                 @PathVariable("commentId") Long commentId) {
        log.info("(Delete) deleteReviewComment - reviewId : {} | commentId : {}", reviewId, commentId);
        reviewService.deleteReviewComment(reviewId, commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
