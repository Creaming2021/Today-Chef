package creaming.dto;

import creaming.domain.etc.Address;
import creaming.domain.member.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;


public class MemberDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberSimpleProfile {
        private Long memberId;
        private String nickname;
        private String profileImage;
        private String phone;

        public MemberSimpleProfile(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.profileImage = member.getProfileImage();
            this.phone = member.getPhone();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberResponse {

        private Long memberId;
        private String nickname;
        private String profileImage;
        private String email;
        private String phone;
        private Address address;

        public MemberResponse(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.profileImage = member.getProfileImage();
            this.email = member.getEmail();
            this.phone = member.getPhone();
            this.address = member.getAddress();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberPostRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String profileImage;
        @NotEmpty
        private String nickname;
        @NotEmpty
        private String phone;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberPutRequest {
        @NotEmpty
        private String nickname;
        @NotEmpty
        private Address address;
        @NotEmpty
        private String phone;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberStudentResponse {
        private Long memberId;
        private String nickname;
        private String profileImage;
        private String phone;

        public MemberStudentResponse(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.profileImage = member.getProfileImage();
            this.phone = member.getPhone();
        }

    }

}
