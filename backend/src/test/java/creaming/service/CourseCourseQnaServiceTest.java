package creaming.service;

import creaming.domain.comment.QnaComment;
import creaming.domain.comment.QnaCommentRepository;
import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.qna.CourseQna;
import creaming.domain.qna.CourseQnaRepository;
import creaming.dto.CourseQnaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CourseCourseQnaServiceTest {

    @Autowired
    CourseQnaRepository courseQnaRepository;

    @Autowired
    CourseQnaService courseQnaService;

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
//    @Rollback(value = false)
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

        for (int i = 1; i <= 20; i++) {
            CourseQna courseQna = CourseQna.builder()
                    .title("호랑이")
                    .content("무서워")
                    .isSecret(false)
                    .build();
            course.addQna(courseQna);
            member.addQna(courseQna);
            courseQnaRepository.save(courseQna);

            for (int j = 1; j <= 10; j++) {
                QnaComment qnaComment = QnaComment.builder()
                        .content("테스트" + i)
                        .build();
                member.addComment(qnaComment);
                courseQna.addComment(qnaComment);
                qnaCommentRepository.save(qnaComment);
            }
        }

        // when


        em.flush();
        em.clear();

        // then
        Course course1 = courseRepository.findAll().get(0);
        Long id = course1.getId();
        System.out.println("id = " + id);

        CourseQna courseQna1 = courseQnaRepository.findAll().get(0);
        System.out.println(courseQna1.getQnaComments().size());

        System.out.println("===========================");

//        Page<CourseQnaDto.Response> qnaAll = courseQnaService.getQnaAll(id, PageRequest.of(1, 5));

        System.out.println("===========================");

        System.out.println("오기유니");

//        qnaAll.getContent().forEach(response -> {
//            System.out.println("이해가 안가");
//            System.out.println(response.getProfile().getNickname());
//            response.getComments().forEach(comment -> System.out.println("comment.getContent() = " + comment.getContent()));
//        });

//        mockMvc.perform(get("/api/qna")
//                .param("courseId", "1")
//                .param("page", "0")
//                .param("size", "10"))
//                .andExpect(status().isOk())
//                .andDo(print());

        System.out.println("코치킴");
    }
}