package creaming.domain.comment;

import creaming.domain.coupon.Coupon;
import creaming.domain.coupon.CouponRepository;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.membercoupon.MemberCoupon;
import creaming.domain.membercoupon.MemberCouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class QnaCommentTest {

    @Autowired
    EntityManager em;
    @Autowired
    QnaCommentRepository qnaCommentRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    MemberCouponRepository memberCouponRepository;

    @Test
    public void A() {

        Member member = Member.builder()
                .nickname("test")
                .build();
        memberRepository.save(member);

        Coupon coupon = Coupon.builder()
                .content("coupon")
                .build();
        Long couponId = couponRepository.save(coupon).getId();

        MemberCoupon memberCoupon = MemberCoupon.builder()
                .member(member)
                .coupon(coupon)
                .build();
        Long memberCouponId = memberCouponRepository.save(memberCoupon).getId();

        em.flush();
        em.clear();

        MemberCoupon findMemberCoupon = memberCouponRepository.findById(memberCouponId).get();
        memberCouponRepository.delete(findMemberCoupon);
//        Coupon findCoupon = couponRepository.findById(couponId).get();
//        couponRepository.delete(findCoupon);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findAll().get(0);
        assertThat(findMember.getMemberCoupons().size()).as("땡").isEqualTo(0);
    }

    @Test
    public void B() {
        Member member = memberRepository.findAll().get(0);
        assertThat(member.getMemberCoupons().size()).as("땡").isEqualTo(0);
    }
}