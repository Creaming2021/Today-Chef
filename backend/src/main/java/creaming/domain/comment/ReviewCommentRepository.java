package creaming.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
}
