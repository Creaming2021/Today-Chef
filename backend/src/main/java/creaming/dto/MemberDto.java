package creaming.dto;

import creaming.domain.etc.Address;
import creaming.domain.member.Member;
import creaming.domain.register.Register;
import lombok.*;

import java.time.DayOfWeek;


public class MemberDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleProfile {
        private Long memberId;
        private String nickname;
        private String profileImage;
        private String phone;

        public SimpleProfile(Member member) {
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
    public static class Response {

        private Long memberId;
        private String nickname;
        private String profileImage;
        private String email;
        private String phone;
        private Address address;

        public Response(Member member) {
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
    public static class PostRequest {
        private String email;
        private String profileImage;
        private String nickname;
        private String phone;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        private String nickname;
        private Address address;
        private String phone;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentResponse {
        private Long memberId;
        private String nickname;
        private String profileImage;
        private String phone;

        public StudentResponse(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.profileImage = member.getProfileImage();
            this.phone = member.getPhone();
        }

    }

}
