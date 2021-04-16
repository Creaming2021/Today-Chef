package creaming.domain.course;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.etc.FoodType;
import creaming.domain.like.Like;
import creaming.domain.member.Member;
import creaming.domain.qna.Qna;
import creaming.domain.register.Register;
import creaming.domain.review.Review;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "courses")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
public class Course extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String name;
    private LocalDateTime date;
    private int price;
    private String image;

    @Enumerated(EnumType.STRING)
    private FoodType category;

    @Lob
    private String materials;

    @Lob
    private String descriptions;

    // 강의 제작자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "course")
    private final List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private final List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private final List<Qna> qnas = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private final List<Review> reviews = new ArrayList<>();
}
