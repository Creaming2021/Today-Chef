package creaming.controller;

import creaming.dto.QnaCommentDto;
import creaming.dto.CourseQnaDto;
import creaming.service.CourseQnaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("course-qna")
@RequiredArgsConstructor
public class CourseQnaController {

    private final CourseQnaService courseQnaService;

    @Operation(summary = "강의 별 모든 질문 조회", description = "courseId에 해당하는 모든 QnA를 페이징 처리해서 출력합니다")
    @GetMapping
    public ResponseEntity<List<CourseQnaDto.CourseQnaResponse>> getQnaAll(@RequestParam("courseId") Long courseId) {
        log.info("(Get) getQnaAll - courseId: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).body(courseQnaService.getQnaAll(courseId));
    }

    @Operation(summary = "질문 조회", description = "qnaId에 해당하는 QnA를 조회합니다")
    @GetMapping("/{qnaId}")
    public ResponseEntity<CourseQnaDto.CourseQnaResponse> getQna(@PathVariable("qnaId") Long qnaId) {
        log.info("(Get) getQna - qnaId: {}", qnaId);
        return ResponseEntity.status(HttpStatus.OK).body(courseQnaService.getQna(qnaId));
    }

    @Operation(summary = "질문 작성", description = "사용자가 특정 강의에 질문을 작성합니다")
    @PostMapping
    public ResponseEntity<Long> postQna(@RequestBody CourseQnaDto.CourseQnaPostRequest dto) {
        log.info("(Post) postQna - memberId: {} | courseId: {} | title: {} | content: {} | isSecret: {}",
                dto.getMemberId(), dto.getCourseId(), dto.getTitle(), dto.getContent(), dto.getIsSecret());
        return ResponseEntity.status(HttpStatus.OK).body(courseQnaService.postQna(dto));
    }

    @Operation(summary = "질문 수정", description = "해당 사용자가 작성한 질문을 수정합니다")
    @PutMapping("/{qnaId}")
    public ResponseEntity<Void> putQna(@PathVariable Long qnaId,
                                    @RequestBody @Valid CourseQnaDto.CourseQnaPutRequest dto) {
        log.info("(Put) putQna - qnaId: {} | title: {} | content: {} | isSecret: {}",
                qnaId, dto.getTitle(), dto.getContent(), dto.getIsSecret());
        courseQnaService.putQna(qnaId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "질문 삭제", description = "해당 사용자가 작성한 질문을 삭제합니다")
    @DeleteMapping("/{qnaId}")
    public ResponseEntity<Void> deleteQna(@PathVariable Long qnaId) {
        log.info("(Delete) deleteQna - qnaId: {}", qnaId);
        courseQnaService.deleteQna(qnaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 해당 질문의 댓글 관리
    @Operation(summary = "해당 QnA의 댓글들 출력", description = "해당 QnA의 댓글 전체를 출력합니다")
    @GetMapping("/{qnaId}/comments")
    public ResponseEntity<List<CourseQnaDto.CourseQnaComment>> getCommentAll(@PathVariable Long qnaId) {
        log.info("(Get) getCommentAll - qnaId: {}", qnaId);
        return ResponseEntity.status(HttpStatus.OK).body(courseQnaService.getCommentAll(qnaId));
    }

    @Operation(summary = "해당 QnA의 댓글 작성", description = "해당 QnA의 댓글을 작성합니다")
    @PostMapping("/{qnaId}/comments")
    public ResponseEntity<Long> postComment(@PathVariable Long qnaId,
                                         @RequestBody @Valid QnaCommentDto.QnaCommentPostRequest dto) {
        // qna 작성자이거나 강의 제작자인지 확인
        log.info("(Post) postComment - qnaId: {} | memberId: {} | content: {}", qnaId, dto.getMemberId(), dto.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(courseQnaService.postComment(qnaId, dto));
    }

    @Operation(summary = "해당 QnA의 댓글 수정", description = "해당 QnA의 댓글을 수정합니다")
    @PutMapping("/{qnaId}/comments/{commentId}")
    public ResponseEntity<Void> putComment(@PathVariable Long qnaId,
                                           @PathVariable Long commentId,
                                           @RequestBody @Valid QnaCommentDto.QnaCommentPutRequest dto) {
        log.info("(Put) putComment - qnaId: {} | commentId: {} | content: {}", qnaId, commentId, dto.getContent());
        courseQnaService.putComment(qnaId, commentId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "해당 QnA의 댓글 삭제", description = "해당 QnA의 댓글을 삭제합니다")
    @DeleteMapping("/{qnaId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long qnaId,
                                           @PathVariable Long commentId) {
        log.info("(Delete) deleteComment - qnaId: {} | commentId: {}", qnaId, commentId);
        courseQnaService.deleteComment(qnaId, commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
