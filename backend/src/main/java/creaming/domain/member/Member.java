package creaming.domain.member;

import creaming.domain.course.Course;
import creaming.domain.etc.Address;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.like.Like;
import creaming.domain.membercoupon.MemberCoupon;
import creaming.domain.qna.Qna;
import creaming.domain.register.Register;
import creaming.domain.review.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private UUID id;

    private String nickname;
    private String profileImage;
    private String email;
    private String kakaoId;

    @Embedded
    private Address address;

    // 수강생의 강의 수강 리스트
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Register> registers = new ArrayList<>();

    // 강사의 강의 제작 리스트
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Qna> qnas = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberCoupon> memberCoupons = new ArrayList<>();


    // MemberCoupon
//    public void addMemberCoupon(MemberCoupon memberCoupon) {
//        this.memberCoupons.add(memberCoupon);
//    }
//
//    public void deleteMemberCoupon(MemberCoupon memberCoupon) {
//        this.memberCoupons.remove(memberCoupon);
//    }
    ///////////////////////////////////////////////////////
}
