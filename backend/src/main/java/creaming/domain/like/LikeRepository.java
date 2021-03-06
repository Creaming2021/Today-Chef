package creaming.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberIdAndCourseId(Long memberId, Long courseId);
    List<Like> findByMemberId(Long memberId);
}
