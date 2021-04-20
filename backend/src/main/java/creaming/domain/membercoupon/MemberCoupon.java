package creaming.domain.membercoupon;

import creaming.domain.coupon.Coupon;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class MemberCoupon extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_coupon_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private LocalDateTime expiredDate;

    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    @Builder
    public MemberCoupon(Member member, Coupon coupon) {
        this.member = member;
        this.coupon = coupon;
        this.expiredDate = getCreatedDate().plusDays(coupon.getExpiredDay());
        this.couponStatus = CouponStatus.AVAILABLE;
    }

}
