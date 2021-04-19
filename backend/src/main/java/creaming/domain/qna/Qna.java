package creaming.domain.qna;

import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;

import javax.persistence.*;
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

    private String subject;
    private String content;
    private boolean isAnswered;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
