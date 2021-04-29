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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public Page<EventDto.SimpleResponse> findEventList(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(EventDto.SimpleResponse::new);
    }

    public EventDto.DetailResponse findEvent(Long eventId) {
        return new EventDto.DetailResponse(eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.EVENT_NOT_FOUND)));
    }

    @Transactional
    public Long saveEvent(EventDto.Request dto) {
        return eventRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.EVENT_NOT_FOUND));
        eventRepository.delete(event);
    }

    @Transactional
    public void updateEvent(Long eventId, EventDto.Request dto) {
        eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.EVENT_NOT_FOUND))
                .update(dto.getTitle(), dto.getContent());
    }
}
