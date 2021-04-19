package creaming.domain.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QnaRepository extends JpaRepository<Qna, UUID> {
}
