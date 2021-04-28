package creaming.service;

import creaming.domain.event.Event;
import creaming.domain.event.EventRepository;
import creaming.dto.EventDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.ErrorState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public UUID saveEvent(EventDto.Request dto) {
        return eventRepository.save(dto.toEntity()).getId();
    }

    public EventDto.DetailResponse findEvent(UUID eventId) {
        return new EventDto.DetailResponse(eventRepository.findById(eventId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND)));
    }
}
