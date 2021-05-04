package creaming.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    List<ProductLike> findByMemberId(Long memberId);
}
