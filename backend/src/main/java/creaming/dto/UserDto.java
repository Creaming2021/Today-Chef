package creaming.dto;

import creaming.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public class UserDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequestDto {
        @NotEmpty
        private String kakaoId;
    }

    @Getter
    @NoArgsConstructor
    public static class LoginResponseDto {
        private Long memberId;
        private String nickname;
        private String email;

        public LoginResponseDto(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.email = member.getEmail();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequestDto {
        @NotEmpty
        private String kakaoId;
        @NotEmpty
        private String email;
        @NotEmpty
        private String nickname;
        @NotEmpty
        private String phone;

        public Member toEntity() {
            return Member.builder()
                    .kakaoId(this.kakaoId)
                    .email(this.email)
                    .nickname(this.nickname)
                    .phone(this.phone)
                    .build();
        }

    }



}
