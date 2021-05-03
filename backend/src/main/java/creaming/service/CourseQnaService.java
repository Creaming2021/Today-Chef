package creaming.service;

import creaming.domain.comment.QnaComment;
import creaming.domain.comment.QnaCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.qna.CourseQna;
import creaming.domain.qna.CourseQnaRepository;
import creaming.dto.QnaCommentDto;
import creaming.dto.CourseQnaDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseQnaService {

    private final CourseQnaRepository courseQnaRepository;
    private final QnaCommentRepository qnaCommentRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public Page<CourseQnaDto.Response> getQnaAll(Long courseId, Pageable pageable) {
        return courseQnaRepository.findByCourseId(courseId, pageable)
                .map(CourseQnaDto.Response::new);
    }

    public CourseQnaDto.Response getQna(Long qnaId) {
        return courseQnaRepository.findById(qnaId)
                .map(CourseQnaDto.Response::new)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
    }

    @Transactional
    public Long postQna(CourseQnaDto.PostRequest dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        CourseQna courseQna = dto.toEntity();
        member.addQna(courseQna);
        course.addQna(courseQna);
        return courseQnaRepository.save(courseQna).getId();
    }

    @Transactional
    public void putQna(Long qnaId, CourseQnaDto.PostRequest dto) {
        CourseQna courseQna = courseQnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        courseQna.update(dto.getTitle(), dto.getContent(), dto.isSecret());
    }

    @Transactional
    public void deleteQna(Long qnaId) {
        CourseQna courseQna = courseQnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        courseQna.getMember().deleteQna(courseQna);
        courseQna.getCourse().deleteQna(courseQna);
        courseQnaRepository.delete(courseQna);
    }

    public List<CourseQnaDto.Comment> getCommentAll(Long qnaId) {
        CourseQna courseQna = courseQnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        return courseQna.getQnaComments().stream()
                .map(CourseQnaDto.Comment::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long postComment(Long qnaId, QnaCommentDto.PostRequest dto) {
        CourseQna courseQna = courseQnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        QnaComment qnaComment = dto.toEntity();
        member.addComment(qnaComment);
        courseQna.addComment(qnaComment);
        return qnaCommentRepository.save(qnaComment).getId();
    }

    @Transactional
    public void putComment(Long qnaId, Long commentId, QnaCommentDto.PutRequest dto) {
        CourseQna courseQna = courseQnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        QnaComment qnaComment = qnaCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_NOT_FOUND));
        qnaComment.update(dto.getContent());
    }

    @Transactional
    public void deleteComment(Long qnaId, Long commentId) {
        QnaComment qnaComment = qnaCommentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_NOT_FOUND));
        CourseQna courseQna = courseQnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.QNA_NOT_FOUND));
        qnaComment.getMember().deleteComment(qnaComment);
        courseQna.deleteComment(qnaComment);
        qnaCommentRepository.delete(qnaComment);
    }
}
