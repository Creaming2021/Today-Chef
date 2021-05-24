package creaming.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    List<ProductLike> findByMemberId(Long memberId);
    Optional<ProductLike> findByMemberIdAndProductId(Long memberId, Long productId);
}
