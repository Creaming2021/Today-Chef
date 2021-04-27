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
        private UUID reviewId;
        private MemberDto.SimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private int commentCount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse {
        private UUID reviewId;
        private MemberDto.SimpleProfile profile;
        private int rating;
        private String title;
        private String content;
        private LocalDateTime date;
        private int commentCount;
        private List<Comment> comments;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private UUID commentId;
        private MemberDto.SimpleProfile profile;
        private String content;
        private LocalDateTime date;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private UUID memberId;
        private UUID courseId;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        private String content;
    }

}
