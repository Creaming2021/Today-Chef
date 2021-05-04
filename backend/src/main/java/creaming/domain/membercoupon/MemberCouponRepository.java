package creaming.domain.membercoupon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    List<MemberCoupon> findByMemberId(Long memberId);
    Optional<MemberCoupon> findByMemberIdAndCouponId(Long memberId, Long couponId);

}
