package creaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.UUID;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    @GetMapping
    public ResponseEntity<?> getReviewAll(@RequestParam("courseId")UUID courseId,
                                          Pageable pageable) {
        return null;
    }
}
