package creaming.domain.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원저장() throws Exception {

        // given
        String email = "ssafy@ssafy.com";
        String nickName = "김싸피";

        // when
        Member member = Member.builder()
                .email(email)
                .nickname(nickName)
                .build();

        UUID id = memberRepository.save(member).getId();

        // then
        Member findMember = memberRepository.findById(id).orElse(null);
        assertThat(findMember.getEmail()).isEqualTo(email);
        assertThat(findMember.getNickname()).isEqualTo(nickName);
    }
}