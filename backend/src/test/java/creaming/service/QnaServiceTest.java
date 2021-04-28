package creaming.service;

import creaming.domain.comment.QnaComment;
import creaming.domain.comment.QnaCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.qna.Qna;
import creaming.domain.qna.QnaRepository;
import creaming.dto.QnaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class QnaServiceTest {

    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    QnaService qnaService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    QnaCommentRepository qnaCommentRepository;

    @Autowired
    EntityManager em;

    @Autowired
    MockMvc mockMvc;

    @Test
    @Rollback(value = false)
    public void 질문_및_댓글_생성() throws Exception {
        // given
        String email = "ssafy@ssafy.com";
        String nickName = "김싸피";

        Member member = Member.builder()
                .email(email)
                .nickname(nickName)
                .build();
        memberRepository.save(member);

        Course course = courseRepository.save(new Course());
        Qna qna = Qna.builder()
                .title("호랑이")
                .content("무서워")
                .isSecret(false)
                .build();
        course.addQna(qna);
        member.addQna(qna);
        qnaRepository.save(qna);

        // when
        for (int i = 1; i <= 10; i++) {
            QnaComment qnaComment = QnaComment.builder()
                    .content("테스트" + i)
                    .build();
            member.addComment(qnaComment);
            qnaCommentRepository.save(qnaComment);
        }

        em.flush();
        em.clear();

        // then
        UUID id = courseRepository.findAll().get(0).getId();
        System.out.println("id = " + id);
        Page<QnaDto.Response> qnaAll = qnaService.getQnaAll(id, PageRequest.of(0, 20));
        System.out.println("오기유니");


//        System.out.println(qnaAll.getContent().size());
        qnaAll.getContent().forEach(response -> {
            System.out.println("이해가 안가");
            System.out.println(response.getProfile().getNickname());
            response.getComments().forEach(comment -> System.out.println("comment.getContent() = " + comment.getContent()));
        });

//        mockMvc.perform(get("/api/qna")
//                .param("courseId", id + "")
//                .param("page", "0")
//                .param("size", "10"))
//                .andExpect(status().isOk())
//                .andDo(print());

        System.out.println("코치킴");
    }
}