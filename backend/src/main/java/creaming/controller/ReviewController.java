package creaming.controller;

import creaming.dto.ReviewCommentDto;
import creaming.dto.ReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    @GetMapping
    public ResponseEntity<?> getReviewAll(@RequestParam("courseId")UUID courseId,
                                          Pageable pageable) {
        List<ReviewDto.SimpleResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable("reviewId") UUID reviewId) {
        ReviewDto.DetailResponse result = new ReviewDto.DetailResponse();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody ReviewDto.PostRequest dto) {
        UUID result = UUID.randomUUID();
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
