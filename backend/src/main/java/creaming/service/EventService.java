<<<<<<< HEAD
package creaming.service;

import creaming.domain.event.Event;
import creaming.domain.event.EventRepository;
import creaming.dto.EventDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public Page<EventDto.SimpleResponse> findEventList(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(event -> new EventDto.SimpleResponse(event.getId(), event.getTitle(), event.getCreatedDate()));
    }

    public EventDto.DetailResponse findEvent(UUID eventId) {
        return new EventDto.DetailResponse(eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.EVENT_NOT_FOUND)));
    }

    @Transactional
    public UUID saveEvent(EventDto.Request dto) {
        return eventRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public void deleteEvent(UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.EVENT_NOT_FOUND));
        eventRepository.delete(event);
    }

    @Transactional
    public void updateEvent(UUID eventId, EventDto.Request dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.EVENT_NOT_FOUND));
        event.update(dto.getTitle(), dto.getContent());
    }
=======
package creaming.service;public class EventService {
>>>>>>> 59bffee (test)
}
