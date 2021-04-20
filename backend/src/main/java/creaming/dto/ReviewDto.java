package creaming.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ReviewDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private UUID id;
        private MemberDto.SimpleProfile profile;
        private int rating;
        private String content;
        private LocalDateTime createdDate;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse {
        private UUID id;
        private MemberDto.SimpleProfile profile;
        private int rating;
        private String content;
        private LocalDateTime createdDate;
        private List<Comment> comments;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private UUID id;
        private MemberDto.SimpleProfile profile;
        private LocalDateTime createdDate;
    }
}
