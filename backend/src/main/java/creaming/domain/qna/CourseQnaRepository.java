package creaming.domain.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseQnaRepository extends JpaRepository<CourseQna, Long> {

    List<CourseQna> findByCourseId(Long courseId);

}
