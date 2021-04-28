package creaming.dto;

import creaming.domain.event.Event;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
        private LocalDateTime date;
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
        private LocalDateTime date;
        private String image;

        public DetailResponse(Event event) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.date = date;
            this.image = image;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;

        public Event toEntity() {
            return Event.builder()
                    .content(this.content)
                    .title(this.title)
                    .build();
        }
    }
}
