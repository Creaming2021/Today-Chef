package creaming.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

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
}