package creaming.service;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.event.Event;
import creaming.domain.event.EventRepository;
import creaming.domain.file.*;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.product.Product;
import creaming.domain.product.ProductRepository;
import creaming.domain.review.CourseReview;
import creaming.domain.review.CourseReviewRepository;
import creaming.domain.review.ProductReview;
import creaming.domain.review.ProductReviewRepository;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import creaming.utils.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    // 클라우드 프론트 사용을 위한 도메인 이름 지정
    private final S3Util s3Util;

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    private final CourseRepository courseRepository;
    private final CourseFileRepository courseFileRepository;

    private final CourseReviewRepository courseReviewRepository;
    private final CourseReviewFileRepository courseReviewFileRepository;

    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;

    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewFileRepository productReviewFileRepository;

    // member 이미지
    public void postMemberImage(Long memberId, MultipartFile file) throws IOException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        member.updateImage(s3Util.uploadFile(file));
    }

    // product 이미지
    public void postProductFile(Long productId, MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        ProductFile productFile = new ProductFile(s3Util.uploadFile(file));
        product.addProductFile(productFile);
        productFileRepository.save(productFile);
    }
    
    // product review 이미지
    public void postProductReviewFile(Long productReviewId, MultipartFile file) throws IOException {
        ProductReview productReview = productReviewRepository.findById(productReviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        ProductReviewFile productReviewFile = new ProductReviewFile(s3Util.uploadFile(file));
        productReview.addProductReviewFile(productReviewFile);
        productReviewFileRepository.save(productReviewFile);
    }
    
    // course 이미지
    public void postCourseFile(Long courseId, MultipartFile file) throws IOException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        CourseFile courseFile = new CourseFile(s3Util.uploadFile(file));
        course.addCourseFile(courseFile);
        courseFileRepository.save(courseFile);
    }

    // course review 이미지
    public void postCourseReviewFile(Long courseReviewId, MultipartFile file) throws IOException {
        CourseReview courseReview = courseReviewRepository.findById(courseReviewId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        CourseReviewFile courseReviewFile = new CourseReviewFile(s3Util.uploadFile(file));
        courseReview.addCourseReviewFile(courseReviewFile);
        courseReviewFileRepository.save(courseReviewFile);
    }
    
    // event 이미지
    public void postEventImage(Long eventId, MultipartFile file) throws IOException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        event.updateImage(s3Util.uploadFile(file));
    }

}
