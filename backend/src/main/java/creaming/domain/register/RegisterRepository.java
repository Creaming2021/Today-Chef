package creaming.domain.register;

import creaming.domain.course.Course;
import creaming.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findByMemberId(Long memberId);
    List<Register> findByCourseId(Long courseId);
    Optional<Register> findByMemberAndCourse(Member member, Course course);
}
