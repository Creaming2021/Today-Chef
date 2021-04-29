package creaming.service;

import creaming.domain.comment.ReviewComment;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.review.Review;
import creaming.domain.review.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void testtest() {

        Member member = Member.builder()
                .email("kim@gmail.com")
                .nickname("킴킴")
                .build();

        memberRepository.save(member);

        Course course = courseRepository.save(new Course());

        for(int i=0; i<5; i++) {
            Review review = Review.builder()
                    .title(" 리뷰인디 " + i)
                    .content(" 내용인디 ")
                    .rating(4)
                    .build();
            reviewRepository.save(review);
            course.addReview(review);
            member.addReview(review);
        }

    }

}