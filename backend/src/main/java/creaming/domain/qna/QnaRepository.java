package creaming.domain.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QPageRequest;

import java.util.UUID;

public interface QnaRepository extends JpaRepository<Qna, UUID> {

    Page<Qna> findByCourseId(UUID courseId, Pageable pageable);

}
