package creaming.controller;

import creaming.dto.QnaCommentDto;
import creaming.dto.QnaDto;
import creaming.service.QnaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    @Operation(summary = "강의 별 모든 질문 조회", description = "courseId에 해당하는 모든 QnA를 페이징 처리해서 출력합니다")
    @GetMapping
    public ResponseEntity<Page<QnaDto.Response>> getQnaAll(@RequestParam("courseId") Long courseId, Pageable pageable) {
        log.info("(Get) getQnaAll - courseId: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).body(qnaService.getQnaAll(courseId, pageable));
    }

    @Operation(summary = "질문 조회", description = "qnaId에 해당하는 QnA를 조회합니다")
    @GetMapping("/{qnaId}")
    public ResponseEntity<QnaDto.Response> getQna(@PathVariable("qnaId") Long qnaId) {
        log.info("(Get) getQna - qnaId: {}", qnaId);
        return ResponseEntity.status(HttpStatus.OK).body(qnaService.getQna(qnaId));
    }

    @Operation(summary = "질문 작성", description = "사용자가 특정 강의에 질문을 작성합니다")
    @PostMapping
    public ResponseEntity<Long> postQna(@RequestBody @Valid QnaDto.PostRequest dto) {
        log.info("(Post) postQna - memberId: {} | courseId: {} | title: {} | content: {} | isSecret: {}",
                dto.getMemberId(), dto.getCourseId(), dto.getTitle(), dto.getContent(), dto.isSecret());
        return ResponseEntity.status(HttpStatus.OK).body(qnaService.postQna(dto));
    }

    @Operation(summary = "질문 수정", description = "해당 사용자가 작성한 질문을 수정합니다")
    @PutMapping("/{qnaId}")
    public ResponseEntity<Void> putQna(@PathVariable Long qnaId,
                                    @RequestBody @Valid QnaDto.PostRequest dto) {
        log.info("(Put) putQna - qnaId: {} | memberId: {} | courseId: {} | title: {} | content: {} | isSecret: {}",
                qnaId, dto.getMemberId(), dto.getCourseId(), dto.getTitle(), dto.getContent(), dto.isSecret());
        qnaService.putQna(qnaId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "질문 삭제", description = "해당 사용자가 작성한 질문을 삭제합니다")
    @DeleteMapping("/{qnaId}")
    public ResponseEntity<Void> deleteQna(@PathVariable Long qnaId) {
        log.info("(Delete) deleteQna - qnaId: {}", qnaId);
        qnaService.deleteQna(qnaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 해당 질문의 댓글 관리
    @Operation(summary = "해당 QnA의 댓글들 출력", description = "해당 QnA의 댓글 전체를 출력합니다")
    @GetMapping("/{qnaId}/comments")
    public ResponseEntity<List<QnaDto.Comment>> getCommentAll(@PathVariable Long qnaId) {
        log.info("(Get) getCommentAll - qnaId: {}", qnaId);
        return ResponseEntity.status(HttpStatus.OK).body(qnaService.getCommentAll(qnaId));
    }

    @Operation(summary = "해당 QnA의 댓글 작성", description = "해당 QnA의 댓글을 작성합니다")
    @PostMapping("/{qnaId}/comments")
    public ResponseEntity<Long> postComment(@PathVariable Long qnaId,
                                         @RequestBody @Valid QnaCommentDto.PostRequest dto) {
        // qna 작성자이거나 강의 제작자인지 확인
        log.info("(Post) postComment - qnaId: {} | memberId: {} | content: {}", qnaId, dto.getMemberId(), dto.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(qnaService.postComment(qnaId, dto));
    }

    @PutMapping("/{qnaId}/comments/{commentId}")
    public ResponseEntity<Void> putComment(@PathVariable Long qnaId,
                                           @PathVariable Long commentId,
                                           @RequestBody @Valid QnaCommentDto.PutRequest dto) {
        log.info("(Put) putComment - qnaId: {} | commentId: {} | content: {}", qnaId, commentId, dto.getContent());
        qnaService.putComment(qnaId, commentId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{qnaId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long qnaId,
                                           @PathVariable Long commentId) {
        log.info("(Delete) deleteComment - qnaId: {} | commentId: {}", qnaId, commentId);
        qnaService.deleteComment(qnaId, commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
