package creaming.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    @Query("select r from Review r join fetch r.member, r.reviewComments where r.course = :course")
    Page<Review> findAllByCourseId(UUID courseId, Pageable pageable);

}
