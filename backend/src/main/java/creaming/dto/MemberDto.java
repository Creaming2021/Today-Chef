package creaming.dto;

import lombok.*;

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
}
