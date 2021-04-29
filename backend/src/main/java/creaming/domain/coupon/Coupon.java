package creaming.domain.coupon;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.membercoupon.MemberCoupon;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@Getter
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Coupon extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String name;

    private String content;

    private Integer discount;

    private Long expiredDay;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberCoupon> memberCoupons = new ArrayList<>();

    // JPA
    public void addMemberCoupons(MemberCoupon memberCoupon) {
        memberCoupons.add(memberCoupon);
        memberCoupon.updateCoupon(this);
    }

    public void deleteMemberCoupons(MemberCoupon memberCoupon) {
        memberCoupons.remove(memberCoupon);
        memberCoupon.updateCoupon(null);
    }
    ////////////////////////////////////////
}