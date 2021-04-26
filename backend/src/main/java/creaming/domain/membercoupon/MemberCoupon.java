package creaming.domain.membercoupon;

import creaming.domain.coupon.Coupon;
import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Builder
@AllArgsConstructor
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

    private LocalDateTime expiredDate;

    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus = CouponStatus.AVAILABLE;

    public void updateExpiredDate() {
        expiredDate =  LocalDateTime.now().plusDays(coupon.getExpiredDay());
    }

    // JPA
    public void updateCoupon(Coupon coupon) {
        this.coupon = coupon;
        updateExpiredDate();
    }

    public void updateMember(Member member) {
        this.member = member;
    }
    ///////////////////////////////
}

