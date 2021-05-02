package creaming.service;

import creaming.domain.coupon.Coupon;
import creaming.domain.coupon.CouponRepository;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.membercoupon.MemberCouponRepository;
import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;
    
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

    // 유저의 쿠폰 리스트 가져오기
    public List<MemberDto.Response> getCouponAll(Long memberId) {

    }
    
    // 유저의 쿠폰 사용하기
    public void useCoupon(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BaseException(ErrorCode.COUPON_NOT_FOUND));


    }
    
    // 유저가 수강한 강의 가져오기
    public List<CourseDto.SimpleResponse> getCourseStudent(Long memberId) {
    }
    
    // 유저가 진행한 강의 가져오기
    public List<CourseDto.SimpleResponse> getCourseTeacher(Long memberId) {
    }

}
