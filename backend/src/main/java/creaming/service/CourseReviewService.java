package creaming.service;

import creaming.domain.comment.ReviewComment;
import creaming.domain.comment.ReviewCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.review.CourseReview;
import creaming.domain.review.CourseReviewRepository;
import creaming.dto.ReviewCommentDto;
import creaming.dto.CourseReviewDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public Page<CourseReviewDto.SimpleResponse> getReviewAll(Long courseId, Pageable pageable) {
        return courseReviewRepository.findAllByCourseId(courseId, pageable)
                .map(CourseReviewDto.SimpleResponse::new);
    }

    public CourseReviewDto.DetailResponse getReview(Long reviewId) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        return new CourseReviewDto.DetailResponse(courseReview);
    }

    @Transactional
    public Long postReview(CourseReviewDto.PostRequest dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        CourseReview courseReview = dto.toEntity();
        member.addReview(courseReview);
        course.addReview(courseReview);

        return courseReviewRepository.save(courseReview).getId();
    }

    @Transactional
    public void putReview(Long reviewId, CourseReviewDto.PutRequest dto) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        courseReview.update(dto.getContent());
    }

    @Transactional
    public Long postReviewComment(Long reviewId, ReviewCommentDto.PostRequest dto) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        ReviewComment reviewComment = dto.toEntity();
        courseReview.addComment(reviewComment);
        member.addComment(reviewComment);

        return reviewCommentRepository.save(reviewComment).getId();
    }

    @Transactional
    public void putReviewComment(Long reviewId, Long commentId, ReviewCommentDto.PutRequest dto) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_COMMENT_NOT_FOUND));
        reviewComment.update(dto.getContent());
    }

    @Transactional
    public void deleteReviewComment(Long reviewId, Long commentId) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_COMMENT_NOT_FOUND));

        courseReview.getMember().deleteReview(courseReview);
        courseReview.getCourse().deleteReview(courseReview);

        courseReview.deleteComment(reviewComment);
        reviewCommentRepository.delete(reviewComment);
    }

}
