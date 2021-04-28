package creaming.domain.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QnaRepository extends JpaRepository<Qna, UUID> {

//    @Query("select q from Qna q join fetch q.member, q.qnaComments where q.course = :courseId")
    Page<Qna> findByCourseId(UUID courseId, Pageable pageable);

    List<Qna> findMelonByCourseId(UUID courseId);

}
