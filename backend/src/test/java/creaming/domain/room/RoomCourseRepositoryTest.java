package creaming.domain.room;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.register.Register;
import creaming.domain.register.RegisterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoomCourseRepositoryTest {

    @Autowired
    CourseRoomRepository courseRoomRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void getCourseRoom() {
        CourseRoom courseRoom = courseRoomRepository.findByToken("sNrOr0SeHe4LPrhvowWKVqiBnR6QGRfE0ZIE5zCR").orElse(null);
        System.out.println(courseRoom.getToken());
    }

    @Test
    @Transactional
    public void getCourse() {
        Course course = courseRoomRepository.findByToken("sNrOr0SeHe4LPrhvowWKVqiBnR6QGRfE0ZIE5zCR").orElse(null).getCourse();
        Member member = memberRepository.findById(1L).orElse(null);

        Register register = registerRepository.findByMemberAndCourse(member, course).orElse(null);

        assertThat(register.getMember().getId()).isEqualTo(1L);

        System.out.println(course.getMember().getId().equals(1L));
//        register.getCourse().getCourseRoom();
        System.out.println(register.getCourse().getCourseRoom());
//        assertThat(register.getCourse().getCourseRoom().getToken()).isEqualTo("9JHgWZ4DK7mLbLBbVjLw0Y2uFLIAce43Hbfx5SE9");
    }
}