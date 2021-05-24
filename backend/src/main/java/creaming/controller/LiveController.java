package creaming.controller;

import creaming.dto.RoomDto;
import creaming.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/live")
public class LiveController {

    private final RoomService roomService;

    @GetMapping("/{userId}/{roomToken}")
    @Operation(summary = "스트리밍 참여", description = "스트리밍 방을 참여 합니다.")
    public ResponseEntity<RoomDto.RoomResponse> joinRoom(@PathVariable("userId") Long userId,
                                              @PathVariable("roomToken") String roomToken) {

        return ResponseEntity.status(HttpStatus.OK).body(roomService.joinRoom(userId, roomToken));
    }
}
