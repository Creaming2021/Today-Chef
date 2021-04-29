package creaming.domain.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    @Query("select q from Qna q join fetch q.member where q.course.id = :courseId")
    Page<Qna> findByCourseId(@Param("courseId") Long courseId, Pageable pageable);

    List<Qna> findMelonByCourseId(UUID courseId);

}
