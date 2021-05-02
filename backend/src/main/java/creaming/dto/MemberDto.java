package creaming.dto;

import creaming.domain.etc.Address;
import creaming.domain.member.Member;
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

        public SimpleProfile(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.profileImage = member.getProfileImage();
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
        private Address address;

        public Response(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.profileImage = member.getProfileImage();
            this.email = member.getEmail();
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
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        private String nickname;
        private Address address;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DayOfWeekResponse {
        private Long memberId;
        private String nickname;
        private String profileImage;
        private String phoneNumber;
        private DayOfWeek dayOfWeek;
    }

}
