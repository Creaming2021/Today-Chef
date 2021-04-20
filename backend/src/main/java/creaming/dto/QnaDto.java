package creaming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class QnaDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private UUID id;
        private String title;
        private String memberName;
        private String courseName;
        private LocalDateTime createdDate;
        private String content;
        private int answerCount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private String memberId;
        private String courseId;
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
}
