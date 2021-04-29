package creaming.domain.register;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.etc.FoodType;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RegisterTest {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 강의_수강_테스트() throws Exception {
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
        teacher.getCourses().add(course);

        Member student = Member.builder()
                .nickname("student")
                .build();
        memberRepository.save(student);

        em.flush();
        em.clear();

        // when
        Course findCourse = courseRepository.findAll().get(0);
        Member findMember = memberRepository.findByNickname("student").get();

        Register register = Register.builder()
                .course(findCourse)
                .member(findMember)
                .price(findCourse.getPrice())
                .build();
        registerRepository.save(register);

        // then
        Register findRegister = registerRepository.findAll().get(0);
        System.out.println("등록 강의 이름 = " + findRegister.getCourse().getName());
        System.out.println("등록 강의 강사 이름 = " + findRegister.getCourse().getMember().getNickname());
        System.out.println("등록 학생 이름 = " + findRegister.getMember().getNickname());

        Member member2 = memberRepository.findByNickname("student").get();
        assertThat(member2.getRegisters().size()).isEqualTo(1);

    }

    @Test
    public void 강의_수강_취소() throws Exception {
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
        teacher.getCourses().add(course);

        Member student = Member.builder()
                .nickname("student")
                .build();
        memberRepository.save(student);

        em.flush();
        em.clear();

        // when
        Member findMember = memberRepository.findByNickname("student").get();

        // then
    }

}