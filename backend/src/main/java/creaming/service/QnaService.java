package creaming.service;

import creaming.domain.comment.QnaComment;
import creaming.domain.comment.QnaCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.qna.Qna;
import creaming.domain.qna.QnaRepository;
import creaming.dto.QnaCommentDto;
import creaming.dto.QnaDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;
    private final QnaCommentRepository qnaCommentRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public Page<QnaDto.Response> getQnaAll(UUID courseId, Pageable pageable) {
        Page<Qna> qnaPage = qnaRepository.findByCourseId(courseId, pageable);
        List<Qna> qnaList = qnaRepository.findMelonByCourseId(courseId);
        System.out.println("전체: " + qnaRepository.findAll().size());
        System.out.println("제발: " + qnaList.size());
        return qnaPage
                .map(QnaDto.Response::new);
    }

    public QnaDto.Response getQna(UUID qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        return new QnaDto.Response(qna);
    }

    @Transactional
    public UUID postQna(QnaDto.PostRequest dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        Qna qna = dto.toEntity();
        member.addQna(qna);
        course.addQna(qna);

        return qnaRepository.save(qna).getId();
    }

    @Transactional
    public void putQna(UUID qnaId, QnaDto.PostRequest dto) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        qna.update(dto.getTitle(), dto.getContent(), dto.isSecret());
    }

    @Transactional
    public void deleteQna(UUID qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        qna.getMember().deleteQna(qna);
        qna.getCourse().deleteQna(qna);
        qnaRepository.delete(qna);
    }

    public List<QnaDto.Comment> getCommentAll(UUID qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        return qna.getQnaComments().stream()
                .map(QnaDto.Comment::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public UUID postComment(UUID qnaId, QnaCommentDto.PostRequest dto) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        QnaComment qnaComment = dto.toEntity();
        member.addComment(qnaComment);
        qna.addComment(qnaComment);
        return qnaCommentRepository.save(qnaComment).getId();
    }

    @Transactional
    public void putComment(UUID qnaId, UUID commentId, QnaCommentDto.PutRequest dto) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        QnaComment qnaComment = qnaCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_NOT_FOUND));
        qnaComment.update(dto.getContent());
    }

    @Transactional
    public void deleteComment(UUID qnaId, UUID commentId) {
        QnaComment qnaComment = qnaCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_NOT_FOUND));
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        qnaComment.getMember().deleteComment(qnaComment);
        qna.deleteComment(qnaComment);
        qnaCommentRepository.delete(qnaComment);
    }
}
