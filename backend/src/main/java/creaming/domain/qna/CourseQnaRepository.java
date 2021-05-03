package creaming.domain.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseQnaRepository extends JpaRepository<CourseQna, Long> {

    Page<CourseQna> findByCourseId(Long courseId, Pageable pageable);

}
