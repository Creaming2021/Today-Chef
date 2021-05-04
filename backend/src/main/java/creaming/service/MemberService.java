package creaming.service;

import creaming.domain.coupon.Coupon;
import creaming.domain.coupon.CouponRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.like.Like;
import creaming.domain.like.LikeRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.membercoupon.MemberCoupon;
import creaming.domain.membercoupon.MemberCouponRepository;
import creaming.domain.register.Register;
import creaming.domain.register.RegisterRepository;
import creaming.dto.CouponDto;
import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.dto.RegisterDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final CouponRepository couponRepository;
    private final RegisterRepository registerRepository;
    private final MemberCouponRepository memberCouponRepository;

    // 모든 유저의 정보 가져오기 
    public List<MemberDto.Response> getMemberAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberDto.Response::new)
                .collect(Collectors.toList());
    }

    // 하나의 유저의 정보 가져오기
    public MemberDto.Response getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        return new MemberDto.Response(member);
    }

    // 유저의 정보 수정하기
    @Transactional
    public void putMember(Long memberId, MemberDto.PutRequest dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        member.update(dto.getNickname(), dto.getAddress(), dto.getPhone());
    }

    // TODO : 유저에게 쿠폰 발급하기
    public void postCoupon(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BaseException(ErrorCode.COUPON_NOT_FOUND));
        // expiredDate = 멤버쿠폰의 생성날짜 (지금의 날짜) + expiredDay



    }

    // TODO : 유저의 쿠폰 리스트 가져오기
    public List<CouponDto.Response> getCouponAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<MemberCoupon> memberCoupons = memberCouponRepository.findByMemberId(member.getId());
        
        // TODO : 쿠폰 expire 처리하기
        
        return memberCoupons.stream()
                .map(CouponDto.Response::new)
                .collect(Collectors.toList());
    }

    // TODO : 유저의 쿠폰 사용하기
    @Transactional
    public void useCoupon(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BaseException(ErrorCode.COUPON_NOT_FOUND));
        MemberCoupon memberCoupon = memberCouponRepository.findByMemberIdAndCouponId(member.getId(), coupon.getId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_COUPON_NOT_FOUND));

        // TODO : expire 확인하기

        memberCoupon.useCoupon();
    }

    // 유저가 수강한 강의 가져오기
    public List<CourseDto.SimpleResponse> getCourseStudent(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Register> registers = registerRepository.findByMemberId(member.getId());
        return registers.stream().map(Register::getCourse)
                .map(CourseDto.SimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 유저가 진행한 강의 가져오기
    public List<CourseDto.SimpleResponse> getCourseTeacher(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Course> courses = courseRepository.findByMemberId(member.getId());
        return courses.stream().map(CourseDto.SimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 유저의 결제 내역 가져오기
    public List<RegisterDto.Response> getRegisterAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Register> registers = registerRepository.findByMemberId(member.getId());
        return registers.stream().map(RegisterDto.Response::new)
                .collect(Collectors.toList());
    }

    // 결제 내역 상세보기
    public RegisterDto.Response getRegister(Long memberId, Long registerId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Register register = registerRepository.findById(registerId)
                .orElseThrow(() -> new BaseException(ErrorCode.REGISTER_NOT_FOUND));
        // memberId와 regiter.getMember().getId가 같은지의 여부
        if(!register.getMember().getId().equals(member.getId())) {
            throw new BaseException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }
        return new RegisterDto.Response(register);
    }

    // 걸제 내역 저장
    @Transactional
    public Long postRegister(Long memberId, RegisterDto.PostRequest dto) {
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

}