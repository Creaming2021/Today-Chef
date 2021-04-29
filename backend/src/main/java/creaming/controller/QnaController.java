package creaming.controller;

import creaming.domain.qna.Qna;
import creaming.dto.QnaCommentDto;
import creaming.dto.QnaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("qna")
public class QnaController {

    @GetMapping
    public ResponseEntity<?> getQnaAll(@RequestParam("courseId") Long courseId,
                                       Pageable pageable) {
        Page<QnaDto.Response> result = Page.empty();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{qnaId}")
    public ResponseEntity<?> getQna(@PathVariable("qnaId") Long qnaLong) {
        QnaDto.Response result = new QnaDto.Response();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<?> postQna(QnaDto.PostRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(1L);
    }

    @PutMapping("/{qnaId}")
    public ResponseEntity<?> putQna(@PathVariable Long qnaId,
                                    QnaDto.PostRequest dto) {
        return ResponseEntity.status(HttpStatus.OK).body(qnaId);
    }

    @DeleteMapping("/{qnaId}")
    public ResponseEntity<?> deleteQna(@PathVariable Long qnaId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 해당 질문의 댓글 관리

    @GetMapping("/{qnaId}/comments")
    public ResponseEntity<?> getCommentAll(@PathVariable Long qnaId) {
        List<QnaCommentDto.Response> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{qnaId}/comments")
    public ResponseEntity<?> postComment(@PathVariable Long qnaId,
                                         QnaCommentDto.PostRequest dto) {
        // qna 작성자이거나 강의 제작자인지 확인

        return ResponseEntity.status(HttpStatus.OK).body(1L);
    }

    @PutMapping("/{qnaId}/comments/{commentId}")
    public ResponseEntity<?> putComment(@PathVariable Long qnaId,
                                        @PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentId);
    }

    @DeleteMapping("/{qnaId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long qnaId,
                                           @PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
