package creaming.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CourseFileRepository extends JpaRepository<CourseFile, Long> {

    Optional<CourseFile> findFirstByCourseId(Long courseId);
}
