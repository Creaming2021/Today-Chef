package creaming.domain.comment;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "comment_id", columnDefinition = "CHAR(32)")
    protected UUID id;

    protected String content;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Member member;

    public Comment(String content) {
        this.content = content;
    }

    public abstract void updateMember(Member member);

    public abstract void updateFK(Object o);
}