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
    public static class DayOfWeekResponse {
        private Long memberId;
        private String nickname;
        private String profileImage;
        private String phone;
        private DayOfWeek dayOfWeek;

        public DayOfWeekResponse(Member member) {
            this.memberId = member.getId();
            this.nickname = member.getNickname();
            this.profileImage = member.getProfileImage();
            this.phone = member.getPhone();
            // todo
        }
    }

}
