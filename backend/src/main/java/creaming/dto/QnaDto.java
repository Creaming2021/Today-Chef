package creaming.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


public class QnaDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long qnaId;
        private MemberDto.SimpleProfile profile;
        private String title;
        private LocalDateTime date;
        private String content;
        private boolean isSecret;
        private List<Comment> comments;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private Long memberId;
        private Long courseId;
        private String title;
        private String content; // TODO editor 로 작업시 수정
        private boolean isSecret;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        private String title;
        private String content;
        private boolean isSecret;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private Long commentId;
        private MemberDto.SimpleProfile profile;
        private String content;
        private LocalDateTime date;
    }
}
