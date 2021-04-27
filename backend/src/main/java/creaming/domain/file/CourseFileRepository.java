package creaming.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseFileRepository extends JpaRepository<CourseFile, UUID> {
}
