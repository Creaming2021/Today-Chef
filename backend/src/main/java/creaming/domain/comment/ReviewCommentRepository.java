package creaming.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, UUID> {
}
