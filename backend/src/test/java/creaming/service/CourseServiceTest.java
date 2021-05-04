package creaming.service;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.etc.FoodType;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.review.CourseReviewRepository;
import creaming.dto.CourseDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.export.elastic.ElasticMetricsExportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseServiceTest {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseReviewRepository courseReviewRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CourseService courseService;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 잘들어가는지() {

        Member member = Member.builder().nickname("닉넴").phone("09000030030").email("kigkgk@test.com").build();
        Member save = memberRepository.save(member);

        em.flush();
        em.clear();

        for(int i=1; i<=10; i++) {
            CourseDto.PostRequest dto = CourseDto.PostRequest.builder()
                    .memberId(save.getId())
                    .name("강읜데여" + i)
                    .date("20210506")
                    .time("11시")
                    .materials("준비물이다.")
                    .descriptions("고운 마음만 들고 오시면 됩니다.")
                    .category(FoodType.DESSERT)
                    .price(800000)
                    .build();
            courseService.postCourse(dto);
        }

    }

}