package creaming.domain.member;

import creaming.domain.cart.Cart;
import creaming.domain.comment.Comment;
import creaming.domain.course.Course;
import creaming.domain.etc.Address;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.like.Like;
import creaming.domain.like.ProductLike;
import creaming.domain.membercoupon.MemberCoupon;
import creaming.domain.order.Order;
import creaming.domain.qna.CourseQna;
import creaming.domain.qna.ProductQna;
import creaming.domain.register.Register;
import creaming.domain.review.CourseReview;
import creaming.domain.review.ProductReview;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;
    private String profileImage;
    private String email;
    private String kakaoId;
    private String phone;

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
    private final List<ProductLike> productLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CourseQna> courseQnas = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CourseReview> courseReviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<MemberCoupon> memberCoupons = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductQna> productQnas = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductReview> productReviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Order> orders = new ArrayList<>();

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

    public void addQna (CourseQna courseQna) {
        courseQnas.add(courseQna);
        courseQna.updateMember(this);
    }

    public void deleteQna (CourseQna courseQna) {
        courseQnas.remove(courseQna);
        courseQna.updateMember(null);
    }

    public void addReview (CourseReview courseReview) {
        courseReviews.add(courseReview);
        courseReview.updateMember(this);
    }

    public void deleteReview (CourseReview courseReview) {
        courseReviews.remove(courseReview);
        courseReview.updateMember(null);
    }

    public void addMemberCoupon(MemberCoupon memberCoupon) {
        memberCoupons.add(memberCoupon);
        memberCoupon.updateMember(this);
    }

    public void deleteMemberCoupon(MemberCoupon memberCoupon) {
        memberCoupons.remove(memberCoupon);
        memberCoupon.updateMember(null);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.updateMember(this);
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
        comment.updateMember(null);
    }

    public void addProductQna(ProductQna productQna) {
        productQnas.add(productQna);
        productQna.updateMember(this);
    }

    public void deleteProductQna(ProductQna productQna) {
        productQnas.remove(productQna);
        productQna.updateMember(null);
    }

    public void addProductReview(ProductReview productReview) {
        productReviews.add(productReview);
        productReview.updateMember(this);
    }

    public void deleteProductReview(ProductReview productReview) {
        productReviews.remove(productReview);
        productReview.updateMember(null);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.updateMember(this);
    }

    public void deleteCart(Cart cart) {
        carts.remove(cart);
        cart.updateMember(null);
    }

    public void addProductLike(ProductLike productLike) {
        productLikes.add(productLike);
        productLike.updateMember(this);
    }

    public void deleteProductLike(ProductLike productLike) {
        productLikes.remove(productLike);
        productLike.updateMember(null);
    }

    //////////////////////////////////////

    public void update(String nickname, Address address, String phone) {
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.updateMember(this);
    }

    public void deleteOrder(Order order) {
        orders.remove(order);
        order.updateMember(null);

    public void updateImage(String profileImage) {
        this.profileImage = profileImage;

    }
}