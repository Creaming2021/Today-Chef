package creaming.domain.course;

import creaming.domain.etc.FoodType;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void 유저의_강의_제작_테스트() {
        // given
        Member teacher = Member.builder()
                .nickname("teacher")
                .email("melon@melon.com")
                .build();
        memberRepository.save(teacher);

        Course course = Course.builder()
                .category(FoodType.DRINK)
//                .date(LocalDateTime.now().plusDays(3))
                .price(10000)
                .materials("밀가루 1kg, 설탕 100g")
                .descriptions("정말 재미있는 클래스 입니다.")
                .name("빵 만들기")
                .member(teacher)
                .build();
        courseRepository.save(course);
        teacher.getCourses().add(course); // 자동 add 가 되지않았다 => cascade all 인 경우? => 변화없음!

        // when
        Member findTeacher = memberRepository.findByNickname("teacher").get();

        // then
        assertThat(findTeacher).isEqualTo(teacher);
        assertThat(findTeacher.getCourses().get(0)).isEqualTo(course);
        System.out.println(course.getMember().getNickname());
        System.out.println(findTeacher.getCourses().get(0).getName());
    }
}