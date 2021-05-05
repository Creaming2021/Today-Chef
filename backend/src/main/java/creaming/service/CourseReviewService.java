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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public List<CourseReviewDto.CourseReviewSimpleResponse> getReviewAll(Long courseId) {
        return courseReviewRepository.findAllByCourseId(courseId).stream()
                .map(CourseReviewDto.CourseReviewSimpleResponse::new)
                .collect(Collectors.toList());
    }

    public CourseReviewDto.CourseReviewDetailResponse getReview(Long reviewId) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        return new CourseReviewDto.CourseReviewDetailResponse(courseReview);
    }

    @Transactional
    public Long postReview(CourseReviewDto.CourseReviewPostRequest dto) {
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
    public void putReview(Long reviewId, CourseReviewDto.CourseReviewPostRequest dto) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        courseReview.update(dto.getTitle(), dto.getContent(), dto.getRating());
    }

    @Transactional
    public Long postReviewComment(Long reviewId, ReviewCommentDto.ReviewCommentPostRequest dto) {
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
    public void putReviewComment(Long reviewId, Long commentId, ReviewCommentDto.ReviewCommentPutRequest dto) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_COMMENT_NOT_FOUND));

        if (!courseReview.getId().equals(reviewComment.getCourseReview().getId())) {
            throw new BaseException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }

        reviewComment.update(dto.getContent());
    }

    @Transactional
    public void deleteReviewComment(Long reviewId, Long commentId) {
        CourseReview courseReview = courseReviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_NOT_FOUND));
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.REVIEW_COMMENT_NOT_FOUND));

        reviewComment.getMember().deleteComment(reviewComment);
        courseReview.deleteComment(reviewComment);

        reviewCommentRepository.delete(reviewComment);
    }

}
