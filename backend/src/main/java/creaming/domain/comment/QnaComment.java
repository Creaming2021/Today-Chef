package creaming.domain.comment;

import creaming.domain.member.Member;
import creaming.domain.qna.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnaComment extends Comment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_id")
    private Qna qna;

    @Builder
    public QnaComment(UUID id, String content, Member member, Qna qna) {
        super(id, content, member);
        this.qna = qna;
    }

    // JPA
    @Override
    public void updateFK(Object qna) {
        this.qna = (Qna) qna;
    }
    ////////////////////////////////////////
}
