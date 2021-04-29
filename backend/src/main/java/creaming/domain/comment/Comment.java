package creaming.domain.comment;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    protected Long id;

    protected String content;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Member member;

    public Comment(String content) {
        this.content = content;
    }

    public abstract void updateMember(Member member);

    public abstract void updateFK(Object o);
}