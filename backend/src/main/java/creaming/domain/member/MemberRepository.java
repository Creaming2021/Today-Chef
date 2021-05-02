package creaming.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long memberId);
    Optional<Member> findByNickname(String nickname);
    Page<Member> findAll(Pageable pageable);

}
