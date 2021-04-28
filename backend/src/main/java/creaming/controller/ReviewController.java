package creaming.controller;

import creaming.dto.ReviewCommentDto;
import creaming.dto.ReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("reviews")
public class ReviewController {

    @GetMapping
    public ResponseEntity<?> getReviewAll(@RequestParam("courseId")Long courseId,
                                          Pageable pageable) {
        List<ReviewDto.SimpleResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable("reviewId") Long reviewId) {
        ReviewDto.DetailResponse result = new ReviewDto.DetailResponse();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody ReviewDto.PostRequest dto) {

        return ResponseEntity.status(HttpStatus.OK).body(1L);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> putReview(@PathVariable("reviewId") Long reviewId,
                                       @RequestBody ReviewDto.PutRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{reviewId}/comments")
    public ResponseEntity<?> postReviewComment(@PathVariable("reviewId") Long reviewId,
                                               @RequestBody ReviewCommentDto.PostRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(1L);
    }

    @PutMapping("/{reviewId}/comments/{commentId}")
    public ResponseEntity<?> putReviewComment(@PathVariable("reviewId") Long reviewId,
                                              @PathVariable("commentId") Long commentId,
                                              @RequestBody ReviewCommentDto.PutRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{reviewId}/comments/{commentId}")
    public ResponseEntity<?> deleteReviewComment(@PathVariable("reviewId") Long reviewId,
                                                 @PathVariable("commentId") Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
