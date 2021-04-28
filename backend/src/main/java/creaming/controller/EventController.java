package creaming.controller;

import creaming.dto.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("events")
public class EventController {

    @GetMapping
    public ResponseEntity<Page<EventDto.SimpleResponse>> getEventList(Pageable pageable) {
        Page<EventDto.SimpleResponse> result = Page.empty();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto.DetailResponse> getEvent(@PathVariable("eventId") UUID eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(new EventDto.DetailResponse());
    }

    @PostMapping
    public ResponseEntity<Long> postEvent(@RequestBody EventDto.Request dto) {
        Long result = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{eventId}/image")
    public ResponseEntity<Void> postEventImage(@PathVariable("eventId") UUID eventId, MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<UUID> putEvent(@PathVariable("eventId") UUID eventId, @RequestBody EventDto.Request dto) {
        return ResponseEntity.status(HttpStatus.OK).body(eventId);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") UUID eventUUID) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}