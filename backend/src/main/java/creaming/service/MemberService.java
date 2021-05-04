package creaming.service;

import creaming.domain.coupon.Coupon;
import creaming.domain.coupon.CouponRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.like.LikeRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;
    private final RegisterRepository registerRepository;
    private final LikeRepository likeRepository;

    // 모든 유저의 정보 가져오기 
    public Page<MemberDto.Response> getMemberAll(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberDto.Response::new);
    }

    // 하나의 유저의 정보 가져오기
    public MemberDto.Response getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        return new MemberDto.Response(member);
    }

    // 유저의 정보 수정하기
    public void putMember(Long memberId, MemberDto.PutRequest dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        member.update(dto.getNickname(), dto.getAddress());
    }

    // TODO : 유저에게 쿠폰 발급하기

    // TODO : 유저의 쿠폰 리스트 가져오기
    public List<CouponDto.Response> getCouponAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        // TODO
        // 1. 다대다 연결테이블 긁어오는 방법을 정확히 모르겠음.
        // 2. memberCoupon이 없는 경우, Error로 처리할지 empty 리스트로 줄지 고민됨.
        // => empty리스트로 주는 경우가 맞아보임. Error로 주면 프론트에서 alert를 띄우는 부분을 변경하셔야할듯?!
        memberCouponRepository.findByMemberId(member.getId());

//        memberCouponRepository.findByMember(member);
        return new ArrayList<>();
    }

    // TODO : 유저의 쿠폰 사용하기
    public void useCoupon(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BaseException(ErrorCode.COUPON_NOT_FOUND));
    }

    // 유저가 수강한 강의 가져오기 -> register 가져오기
    public List<CourseDto.SimpleResponse> getCourseStudent(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Register> registers = registerRepository.findByMember(member);
        return registers.stream().map(Register::getCourse)
                .map(CourseDto.SimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 유저가 진행한 강의 가져오기 -> course 가져오기
    public List<CourseDto.SimpleResponse> getCourseTeacher(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Course> courses = courseRepository.findByMember(member);
        return courses.stream().map(CourseDto.SimpleResponse::new)
                .collect(Collectors.toList());
    }


    // 유저의 결제 내역 가져오기
    public List<RegisterDto.SimpleResponse> getRegisterAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        List<Register> registers = registerRepository.findByMember(member);
        return registers.stream().map(RegisterDto.SimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 결제 내역 상세보기
    public RegisterDto.DetailResponse getRegister(Long memberId, Long registerId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Register register = registerRepository.findById(registerId)
                .orElseThrow(() -> new BaseException(ErrorCode.REGISTER_NOT_FOUND));
        // memberId와 regiter.getMember().getId가 같은지의 여부
        if(register.getMember().getId() != member.getId()) {
            throw new BaseException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }
        return new RegisterDto.DetailResponse(register);
    }

    // 걸제 내역 저장
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

    // TODO : 좋아요 토글
    public void toggleLike(Long memberId, Long courseId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));

    }

}