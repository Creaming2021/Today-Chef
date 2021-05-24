package creaming.dto;

import creaming.domain.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Test;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class EventDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventSimpleResponse {
        private Long id;
        private String title;
        private LocalDateTime date;
        private String image;

        public EventSimpleResponse(Event event) {
            this.id = event.getId();
            this.title = event.getTitle();
            this.date = event.getCreatedDate();
            this.image = event.getImage();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventDetailResponse {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime date;
        private String image;

        public EventDetailResponse(Event event) {
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
    public static class EventRequest {
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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventImageDto {
        private Long eventId;
        private String image;

        public EventImageDto(Event event) {
            this.eventId = event.getId();
            this.image = event.getImage();
        }
    }

}
