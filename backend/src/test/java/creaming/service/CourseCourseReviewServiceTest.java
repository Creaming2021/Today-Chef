package creaming.service;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.review.CourseReview;
import creaming.domain.review.CourseReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CourseCourseReviewServiceTest {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseReviewRepository courseReviewRepository;
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
            CourseReview courseReview = CourseReview.builder()
                    .title(" 리뷰인디 " + i)
                    .content(" 내용인디 ")
                    .rating(4)
                    .build();
            courseReviewRepository.save(courseReview);
            course.addReview(courseReview);
            member.addReview(courseReview);
        }

    }

}