package creaming.domain.comment;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;

import javax.persistence.*;
import java.util.UUID;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Comment extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private UUID id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
