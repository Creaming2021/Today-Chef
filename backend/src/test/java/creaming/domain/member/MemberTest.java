package creaming.domain.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원저장() throws Exception {
        // given
        Member member = new Member();

        // when
        memberRepository.save(member);

        // then
        Member findMember = memberRepository.findAll().get(0);
        System.out.println("findMember.getId() = " + findMember.getId());
        System.out.println("findMember.getCreatedDate() = " + findMember.getCreatedDate());
        assertThat(findMember).isEqualTo(member);
    }
}