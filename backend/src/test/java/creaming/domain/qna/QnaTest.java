package creaming.domain.qna;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QnaTest {

    @Autowired CourseRepository courseRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired QnaRepository qnaRepository;

    @Test
    @DisplayName("Qna 등록")
    public void addQna() {

        String nickname = "김싸피";
        Member.builder()
                .nickname(nickname)
                .build();
    }
}