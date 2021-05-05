package creaming.controller;

import creaming.dto.EventDto;
import creaming.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    @Operation(summary = "이벤트 리스트 조회", description = "이벤트 리스트를 조회합니다.")
    public ResponseEntity<List<EventDto.EventSimpleResponse>> getEventList() {
        log.info("(Get) getEventList");
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findEventList());
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "이벤트 상세 조회", description = "이벤트를 상세 조회합니다.")
    public ResponseEntity<EventDto.EventDetailResponse> getEvent(@PathVariable("eventId") Long eventId) {
        log.info("(Get) getEvent - eventID : {}", eventId);
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findEvent(eventId));
    }

    @PostMapping
    @Operation(summary = "이벤트 틍록", description = "이벤트를 등록합니다.")
    public ResponseEntity<Long> postEvent(@RequestBody @Valid EventDto.EventRequest dto) {
        log.info("(Post) postEvent - title : {} | content : {}", dto.getTitle(), dto.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.saveEvent(dto));
    }

    @PutMapping("/{eventId}")
    @Operation(summary = "이벤트 수정", description = "이벤트를 수정합니다.")
    public ResponseEntity<Void> putEvent(@PathVariable("eventId") Long eventId, @RequestBody EventDto.EventRequest dto) {
        log.info("(Put) putEvent - title : {} | content : {}", dto.getTitle(), dto.getContent());
        eventService.updateEvent(eventId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{eventId}")
    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제합니다.")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") Long eventId) {
        log.info("(Delete) deleteEvent - eventID : {}", eventId);
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
