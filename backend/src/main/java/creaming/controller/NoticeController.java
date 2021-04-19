package creaming.controller;

import creaming.domain.notice.Notice;
import creaming.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("notices")
public class NoticeController {

    @GetMapping
    public ResponseEntity<Page<NoticeDto.SimpleResponse>> getNoticeList(Pageable pageable) {
        Page<NoticeDto.SimpleResponse> result = Page.empty();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDto.DetailResponse> getNotice(@PathVariable("noticeId") Long noticeId) {
        return ResponseEntity.status(HttpStatus.OK).body(new NoticeDto.DetailResponse());
    }

    @PostMapping
    public ResponseEntity<Long> postNotice(@RequestBody NoticeDto.Request dto) {
        Long result = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{noticeId}/image")
    public ResponseEntity<Void> postNoticeImage(@PathVariable("noticeId") Long noticeId, MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<Long> putNotice(@PathVariable("noticeId") Long noticeId, @RequestBody NoticeDto.Request dto) {
        return ResponseEntity.status(HttpStatus.OK).body(noticeId);
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable("noticeId") Long noticeId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
