package creaming.domain.register;

import creaming.domain.member.Member;
import creaming.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findByMember(Member member);
    List<Register> findByCourse(Course course);
}
