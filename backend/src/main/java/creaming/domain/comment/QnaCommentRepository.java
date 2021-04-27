package creaming.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QnaCommentRepository extends JpaRepository<QnaComment, UUID> {
}
