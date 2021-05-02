package creaming.domain.membercoupon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    Optional<MemberCoupon> findByMemberId(Long memberId);

}
