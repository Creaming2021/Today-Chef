package creaming.controller;

import creaming.dto.QnaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.UUID;

@RestController
@RequestMapping("courses")
public class CourseController {

    // 해당 강의의 질문 리스트 출력
    @GetMapping("/{courseUUID}/qna")
    public ResponseEntity<Page<QnaDto.Response>> getQnaListByCourse(@PathVariable("courseUUID") UUID courseUUID,
                                             Pageable pageable) {
        Page<QnaDto.Response> result = Page.empty();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
