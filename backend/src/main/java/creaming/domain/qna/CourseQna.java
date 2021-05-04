package creaming.domain.qna;

import creaming.domain.comment.QnaComment;
import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_qnas")
@EqualsAndHashCode(of = "id", callSuper = false)
public class CourseQna extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_qna_id")
    private Long id;

    private String title;
    private String content;
    private Boolean isSecret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "courseQna", cascade = CascadeType.ALL, orphanRemoval = true)
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
