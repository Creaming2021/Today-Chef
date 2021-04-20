package creaming.domain.membercoupon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, UUID> {
}
