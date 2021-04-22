package creaming.dto;

import creaming.domain.etc.Address;
import lombok.*;

import java.time.DayOfWeek;
import java.util.UUID;

public class MemberDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleProfile {
        private UUID memberId;
        private String nickname;
        private String profileImage;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private UUID memberId;
        private String nickname;
        private String profileImage;
        private String email;
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
        private UUID memberId;
        private String nickname;
        private String profileImage;
        private String phoneNumber;
        private DayOfWeek dayOfWeek;
    }

}
