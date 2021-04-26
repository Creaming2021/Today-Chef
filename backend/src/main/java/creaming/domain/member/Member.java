package creaming.domain.member;

import creaming.domain.course.Course;
import creaming.domain.etc.Address;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.file.CourseFile;
import creaming.domain.like.Like;
import creaming.domain.membercoupon.MemberCoupon;
import creaming.domain.qna.Qna;
import creaming.domain.register.Register;
import creaming.domain.review.Review;
import creaming.domain.timetable.TimeTable;
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

    // JPA
    public void addRegister(Register register) {
        registers.add(register);
        register.updateMember(this);
    }

    public void deleteRegister(Register register) {
        registers.remove(register);
        register.updateMember(null);
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.updateMember(this);
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
        course.updateMember(null);
    }

    public void addLike (Like like) {
        likes.add(like);
        like.updateMember(this);
    }

    public void deleteLike (Like like) {
        likes.remove(like);
        like.updateMember(null);
    }

    public void addQna (Qna qna) {
        qnas.add(qna);
        qna.updateMember(this);
    }

    public void deleteQna (Qna qna) {
        qnas.remove(qna);
        qna.updateMember(null);
    }

    public void addReview (Review review) {
        reviews.add(review);
        review.updateMember(this);
    }

    public void deleteReview (Review review) {
        reviews.remove(review);
        review.updateMember(null);
    }

    public void addMemberCoupon(MemberCoupon memberCoupon) {
        memberCoupons.add(memberCoupon);
        memberCoupon.updateMember(this);
    }

    public void deleteMemberCoupon(MemberCoupon memberCoupon) {
        memberCoupons.remove(memberCoupon);
        memberCoupon.updateMember(null);
    }
    //////////////////////////////////////
}
