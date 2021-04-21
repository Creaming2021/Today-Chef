package creaming.domain.qna;

import creaming.domain.comment.Comment;
import creaming.domain.comment.QnaComment;
import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;

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

    @Id @GeneratedValue
    @Column(name = "qna_id")
    private UUID id;

    private String title;
    private String content;
    private boolean isSecret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<QnaComment> qnaComments = new ArrayList<>();
}
