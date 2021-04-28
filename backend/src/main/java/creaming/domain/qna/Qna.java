package creaming.domain.qna;

import creaming.domain.comment.Comment;
import creaming.domain.comment.QnaComment;
import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import creaming.dto.QnaDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qnas")
@EqualsAndHashCode(of = "id", callSuper = false)
public class Qna extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "qna_id", columnDefinition = "CHAR(32)")
    private UUID id;

    private String title;
    private String content;
    private Boolean isSecret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QnaComment> qnaComments = new ArrayList<>();

    // JPA
    public void updateCourse(Course course) {
        this.course = course;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void addComment(QnaComment qnaComment) {
        qnaComments.add(qnaComment);
        qnaComment.updateFK(this);
    }

    public void deleteComment(QnaComment qnaComment) {
        qnaComments.remove(qnaComment);
        qnaComment.updateFK(null);
    }
    /////////////////////////////////

    public void update(String title, String content, Boolean isSecret) {
        this.title = title;
        this.content = content;
        this.isSecret = isSecret;
    }

}
