package creaming.dto;

import creaming.domain.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

        public SimpleResponse(Event event) {
            this.id = event.getId();
            this.title = event.getTitle();
            this.date = getDate();
        }
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
            this.id = event.getId();
            this.title = event.getTitle();
            this.content = event.getContent();
            this.date = event.getCreatedDate();
            this.image = event.getImage();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotEmpty
        private String title;
        @NotNull
        private String content;

        public Event toEntity() {
            return Event.builder()
                    .content(this.content)
                    .title(this.title)
                    .build();
        }
    }
}
