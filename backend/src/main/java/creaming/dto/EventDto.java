package creaming.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private UUID id;
        private String title;
        private LocalDateTime createdDate;
        private String image;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse {
        private UUID id;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private String image;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String title;
        private String content;
    }

}
