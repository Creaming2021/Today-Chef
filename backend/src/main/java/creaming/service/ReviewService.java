package creaming.service;

import creaming.domain.comment.ReviewCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.review.Review;
import creaming.domain.review.ReviewRepository;
import creaming.dto.ReviewDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public Page<ReviewDto.SimpleResponse> getReviewAll(UUID courseId, Pageable pageable) {
        return reviewRepository.findAllByCourseId(courseId, pageable)
                .map(ReviewDto.SimpleResponse::new);
    }

    public ReviewDto.DetailResponse getReview(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.RUNTIME_EXCEPTION));
        return new ReviewDto.DetailResponse(review);
    }

    @Transactional
    public UUID postReview(ReviewDto.PostRequest dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new BaseException(ErrorCode.RUNTIME_EXCEPTION));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.RUNTIME_EXCEPTION));
        return reviewRepository.save(new Review(dto, member, course)).getId();
    }

}
