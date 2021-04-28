package creaming.service;

import creaming.domain.comment.QnaCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.qna.Qna;
import creaming.domain.qna.QnaRepository;
import creaming.dto.QnaDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;
    private final QnaCommentRepository qnaCommentRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    public Page<QnaDto.Response> getQnaAll(UUID courseId, Pageable pageable) {
        return qnaRepository.findByCourseId(courseId, pageable)
                .map(QnaDto.Response::new);
    }

    public QnaDto.Response getQna(UUID qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.RUNTIME_EXCEPTION));
        return new QnaDto.Response(qna);
    }

    @Transactional
    public UUID postQna(QnaDto.PostRequest dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new BaseException(ErrorCode.RUNTIME_EXCEPTION));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.RUNTIME_EXCEPTION));
        Qna qna = Qna.builder()
                .isSecret(dto.isSecret())
                .content(dto.getContent())
                .title(dto.getTitle())
                .build();
        member.addQna(qna);
        course.addQna(qna);
        qnaRepository.save(qna);

        return null;
    }

}
