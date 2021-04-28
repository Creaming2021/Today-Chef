package creaming.domain.member;

import creaming.domain.comment.QnaComment;
import creaming.domain.comment.QnaCommentRepository;
import creaming.domain.comment.ReviewComment;
import creaming.domain.comment.ReviewCommentRepository;
import creaming.domain.qna.Qna;
import creaming.domain.qna.QnaRepository;
import creaming.domain.review.Review;
import creaming.domain.review.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    QnaCommentRepository qnaCommentRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewCommentRepository reviewCommentRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void beforeEach() {

        String email = "ssafy@ssafy.com";
        String nickName = "김싸피";

        Member member = Member.builder()
                .email(email)
                .nickname(nickName)
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("멤버 정보 조회")
    public void findMember() {
        // Given
        String email = "ssafy@ssafy.com";
        String nickName = "김싸피";

        // When
        Member member = memberRepository.findByNickname(nickName).get();

        // Then
        assertThat(member.getNickname()).as("fail nickname").isEqualTo(nickName);
        assertThat(member.getEmail()).as("fail email").isEqualTo(email);
    }

    @Test
    public void Comment_조회() throws Exception {
        // given
        Member member = memberRepository.findByNickname("김싸피").get();
        Qna qna = Qna.builder()
                .title("호랑이")
                .content("무서워")
                .isSecret(false)
                .build();
        member.addQna(qna);
        qnaRepository.save(qna);

        Review review = new Review();
        member.addReview(review);
        reviewRepository.save(review);

        // when
        for (int i = 1; i <= 10; i++) {
            QnaComment qnaComment = QnaComment.builder()
                    .content("테스트" + i)
                    .build();
            member.addComment(qnaComment);
            qnaCommentRepository.save(qnaComment);
        }

        for (int i = 1; i <= 10; i++) {
            ReviewComment reviewComment = ReviewComment.builder()
                    .content("테스트" + i)
                    .build();
            member.addComment(reviewComment);
            reviewCommentRepository.save(reviewComment);
        }

        em.flush();
        em.clear();

        // then
        System.out.println(qnaCommentRepository.findAll().size());
        System.out.println(reviewCommentRepository.findAll().size());

        Member findMember = memberRepository.findByNickname("김싸피").get();
        System.out.println(findMember.getComments().size());
    }
}