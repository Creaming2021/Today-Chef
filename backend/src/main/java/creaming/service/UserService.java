package creaming.service;

import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.dto.UserDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final MemberRepository memberRepository;

    // 로그인
    public UserDto.LoginResponseDto login(String kakaoId) {
        return memberRepository.findByKakaoId(kakaoId)
                .map(UserDto.LoginResponseDto::new)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
    }

    // 닉네임 중복 확인
    public boolean checkEmail(String email) {
        return memberRepository.findByEmail(email).isEmpty();
    }

    // 회원가입
    public UserDto.LoginResponseDto signUp(UserDto.SignupRequestDto dto) {
        Member member = memberRepository.save(dto.toEntity());
        return new UserDto.LoginResponseDto(member);
    }


}
