package creaming.service;

import creaming.domain.cart.Cart;
import creaming.domain.cart.CartRepository;
import creaming.domain.coupon.Coupon;
import creaming.domain.coupon.CouponRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.like.Like;
import creaming.domain.like.LikeRepository;
import creaming.domain.like.ProductLike;
import creaming.domain.like.ProductLikeRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.membercoupon.CouponStatus;
import creaming.domain.membercoupon.MemberCoupon;
import creaming.domain.membercoupon.MemberCouponRepository;
import creaming.domain.product.Product;
import creaming.domain.product.ProductRepository;
import creaming.domain.register.Register;
import creaming.domain.register.RegisterRepository;
import creaming.dto.*;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final CartRepository cartRepository;
    private final CourseRepository courseRepository;
    private final CouponRepository couponRepository;
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;
    private final RegisterRepository registerRepository;

    // 모든 유저의 정보 가져오기 
    public List<MemberDto.MemberResponse> getMemberAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberDto.MemberResponse::new)
                .collect(Collectors.toList());
    }

    // 하나의 유저의 정보 가져오기
    public MemberDto.MemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        return new MemberDto.MemberResponse(member);
    }

    // 유저의 정보 수정하기
    @Transactional
    public void putMember(Long memberId, MemberDto.MemberPutRequest dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        member.update(dto.getNickname(), dto.getAddress(), dto.getPhone());
    }

    // 유저에게 쿠폰 발급하기
    public void postCoupon(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BaseException(ErrorCode.COUPON_NOT_FOUND));
        // expiredDate = 멤버쿠폰의 생성날짜 (지금의 날짜) + expiredDay
        MemberCoupon memberCoupon = MemberCoupon.builder()
                .member(member)
                .coupon(coupon)
                .expiredDate(LocalDateTime.now().plusDays(coupon.getExpiredDay()))
                .couponStatus(CouponStatus.AVAILABLE)
                .build();
        memberCouponRepository.save(memberCoupon);
        member.addMemberCoupon(memberCoupon);
        coupon.addMemberCoupons(memberCoupon);
    }

    // 유저의 쿠폰 리스트 가져오기
    public List<CouponDto.CouponResponse> getCouponAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<MemberCoupon> memberCoupons = memberCouponRepository.findByMemberId(member.getId());
        
        // 쿠폰 expire 처리
        memberCouponRepository.findByMemberId(member.getId()).stream().filter(memberCoupon -> LocalDateTime.now().isAfter(memberCoupon.getExpiredDate()))
                .forEach(MemberCoupon::expireCoupon);

        return memberCouponRepository.saveAll(memberCoupons).stream()
                .map(CouponDto.CouponResponse::new)
                .collect(Collectors.toList());
    }

    // 유저의 쿠폰 사용하기
    @Transactional
    public void useCoupon(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BaseException(ErrorCode.COUPON_NOT_FOUND));
        MemberCoupon memberCoupon = memberCouponRepository.findByMemberIdAndCouponId(member.getId(), coupon.getId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_COUPON_NOT_FOUND));

        // 만료되었거나 사용한 쿠폰
        if(LocalDateTime.now().isAfter(memberCoupon.getExpiredDate())
                || memberCoupon.getCouponStatus().equals(CouponStatus.USED)) {
            throw new BaseException(ErrorCode.MEMBER_COUPON_NOT_FOUND);
        }
        memberCoupon.useCoupon();
    }

    // 유저가 수강한 강의 가져오기
    public List<CourseDto.CourseSimpleResponse> getCourseStudent(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Register> registers = registerRepository.findByMemberId(member.getId());
        return registers.stream().map(Register::getCourse)
                .map(CourseDto.CourseSimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 유저가 진행한 강의 가져오기
    public List<CourseDto.CourseSimpleResponse> getCourseTeacher(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Course> courses = courseRepository.findByMemberId(member.getId());
        return courses.stream().map(CourseDto.CourseSimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 유저의 결제 내역 가져오기
    public List<RegisterDto.RegisterResponse> getRegisterAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Register> registers = registerRepository.findByMemberId(member.getId());
        return registers.stream().map(RegisterDto.RegisterResponse::new)
                .collect(Collectors.toList());
    }

    // 결제 내역 상세보기
    public RegisterDto.RegisterResponse getRegister(Long memberId, Long registerId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Register register = registerRepository.findById(registerId)
                .orElseThrow(() -> new BaseException(ErrorCode.REGISTER_NOT_FOUND));
        // memberId와 regiter.getMember().getId가 같은지의 여부
        if(!register.getMember().getId().equals(member.getId())) {
            throw new BaseException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }
        return new RegisterDto.RegisterResponse(register);
    }

    // 걸제 내역 저장
    @Transactional
    public Long postRegister(Long memberId, RegisterDto.RegisterPostRequest dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        Register register = dto.toEntity();
        course.addRegister(register);
        member.addRegister(register);

        return registerRepository.save(register).getId();
    }

    // 좋아요 토글
    @Transactional
    public void toggleLike(Long memberId, Long courseId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));

        Optional<Like> likeOpt = likeRepository.findByMemberIdAndCourseId(member.getId(), course.getId());
        if(likeOpt.isEmpty()){ // 좋아요가 없으면 추가
            Like like = Like.builder().member(member).course(course).build();
            likeRepository.save(like);
            member.addLike(like);
            course.addLike(like);
        }else { // 좋아요가 있으면 제거
            likeOpt.get().getMember().deleteLike(likeOpt.get());
            course.deleteLike(likeOpt.get());
            likeRepository.delete(likeOpt.get());
        }
    }

    // 강의 좋아요 리스트
    public List<CourseDto.CourseSimpleResponse> getCourseLike(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Like> likes = likeRepository.findByMemberId(member.getId());

        return likes.stream().map(Like::getCourse)
                .map(CourseDto.CourseSimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 키트 좋아요 리스트
    public List<ProductDto.ProductSimpleResponse> getProductLike(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<ProductLike> productLikes = productLikeRepository.findByMemberId(member.getId());

        return productLikes.stream().map(ProductLike::getProduct)
                .map(ProductDto.ProductSimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 장바구니 목록 불러오기
    public List<CartDto.CartResponse> getCarts(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Cart> carts = cartRepository.findByMemberId(member.getId());

        return carts.stream()
                .map(CartDto.CartResponse::new)
                .collect(Collectors.toList());
    }

    // 장바구니 담기
    @Transactional
    public Long postCart(Long memberId, CartDto.CartPostRequest dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        Cart cart = dto.toEntity();
        member.addCart(cart);
        product.addCart(cart);

        return cartRepository.save(cart).getId();
    }

    // 장바구니 제거
    @Transactional
    public void deleteCart(Long memberId, Long productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        Cart cart = cartRepository.findByMemberIdAndProductId(memberId, productId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        cart.getMember().deleteCart(cart);
        product.deleteCart(cart);
        member.deleteCart(cart);
        cartRepository.delete(cart);
    }

}