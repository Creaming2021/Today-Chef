package creaming.service;

import creaming.domain.comment.ReviewComment;
import creaming.domain.comment.ReviewCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.review.Review;
import creaming.domain.review.ReviewRepository;
import creaming.dto.ReviewCommentDto;
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
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        return new ReviewDto.DetailResponse(review);
    }

    @Transactional
    public UUID postReview(ReviewDto.PostRequest dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        Review review = dto.toEntity();
        member.addReview(review);
        course.addReview(review);

        return reviewRepository.save(review).getId();
    }

    @Transactional
    public void putReview(UUID reviewId, ReviewDto.PutRequest dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        review.update(dto.getContent());
    }

    @Transactional
    public UUID postReviewComment(UUID reviewId, ReviewCommentDto.PostRequest dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        ReviewComment reviewComment = dto.toEntity();
        review.addComment(reviewComment);
        member.addComment(reviewComment);

        return reviewCommentRepository.save(reviewComment).getId();
    }

    @Transactional
    public void putReviewComment(UUID reviewId, UUID commentId, ReviewCommentDto.PutRequest dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_COMMENT_NOT_FOUND));
        reviewComment.update(dto.getContent());
    }

    @Transactional
    public void deleteReviewComment(UUID reviewId, UUID commentId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_COMMENT_NOT_FOUND));

        review.deleteComment(reviewComment);
        reviewCommentRepository.delete(reviewComment);
    }

}
