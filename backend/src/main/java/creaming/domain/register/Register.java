package creaming.domain.register;

import creaming.domain.course.Course;
import creaming.domain.delivery.Delivery;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registers")
@EqualsAndHashCode(of = "id", callSuper = false)
public class Register extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private Long id;

    private DayOfWeek dayOfWeek;

    private int price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    // JPA
    public void updateCourse(Course course) {
        this.course = course;
    }

    public void updateMember(Member member) {
        this.member = member;
    }
    /////////////////////////////
}
