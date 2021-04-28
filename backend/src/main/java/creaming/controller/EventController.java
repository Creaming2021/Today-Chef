package creaming.controller;

import creaming.dto.EventDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import creaming.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    @GetMapping
//    @Operation(summary = "이벤트 리스트 조회", description = "이벤트 리스트를 조회합니다.")
    public ResponseEntity<Page<EventDto.SimpleResponse>> getEventList(Pageable pageable) {
        Page<EventDto.SimpleResponse> result = Page.empty();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "이벤트 상세 조회", description = "이벤트를 상세 조회합니다.")
    public ResponseEntity<EventDto.DetailResponse> getEvent(@PathVariable("eventId") UUID eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findEvent(eventId));
    }

    @PostMapping
    @Operation(summary = "이벤트 틍록", description = "이벤트를 등록합니다.")
    public ResponseEntity<UUID> postEvent(@RequestBody @Valid EventDto.Request dto) {
        log.info("(Post) postEvent : {}", dto.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.saveEvent(dto));
    }

    @PostMapping("/{eventId}/image")
    @Operation(summary = "이벤트 이미지 저장", description = "이벤트 이미지를 저장합니다.")
    public ResponseEntity<Void> postEventImage(@PathVariable("eventId") UUID eventId, MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{eventId}")
//    @Operation(summary = "이벤트 수정", description = "이벤트를 수정합니다.")
    public ResponseEntity<UUID> putEvent(@PathVariable("eventId") UUID eventId, @RequestBody EventDto.Request dto) {
        return ResponseEntity.status(HttpStatus.OK).body(eventId);
    }

    @DeleteMapping("/{eventId}")
//    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제합니다.")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") UUID eventUUID) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
