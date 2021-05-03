package creaming.domain.comment;

import creaming.domain.member.Member;
import creaming.domain.qna.CourseQna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnaComment extends Comment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_qna_id")
    private CourseQna courseQna;

    @Builder
    public QnaComment(String content) {
        super(content);
    }

    // JPA
    @Override
    public void updateMember(Member member) {
        this.member = member;
    }

    @Override
    public void updateFK(Object qna) {
        this.courseQna = (CourseQna) qna;
    }

    ////////////////////////////////////////
    public void update(String content) {
        this.content = content;
    }
}