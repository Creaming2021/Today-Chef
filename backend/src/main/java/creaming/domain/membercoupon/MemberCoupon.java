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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class MemberCoupon extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_coupon_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    //
//    private final LocalDateTime expiredDate =  LocalDateTime.now().plusDays(coupon.getExpiredDay());
//
//    @Enumerated(EnumType.STRING)
//    private final CouponStatus couponStatus = CouponStatus.AVAILABLE;

    // Member, Coupon
//    public void addMemberCoupn(Member member, Coupon coupon) {
//        this.member = member;
//        this.coupon = coupon;
//        member.addMemberCoupon(this);
//        coupon.addMemberCoupon(this);
//    }
//
//    public void deleteMemberCoupon() {
//        this.member.deleteMemberCoupon(this);
//        this.coupon.deleteMemberCoupon(this);
//        this.member = null;
//        this.coupon = null;
//    }
    ////////////////////////////////
}

