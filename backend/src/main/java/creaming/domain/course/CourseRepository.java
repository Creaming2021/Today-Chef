package creaming.domain.course;

import creaming.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByMember(Member member);
}
