package creaming.domain.course;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.etc.FoodType;
import creaming.domain.file.CourseFile;
import creaming.domain.like.Like;
import creaming.domain.member.Member;
import creaming.domain.qna.Qna;
import creaming.domain.register.Register;
import creaming.domain.review.Review;
import creaming.domain.timetable.TimeTable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Getter
@Table(name = "courses")
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "course_id", columnDefinition = "CHAR(32)")
    private UUID id;

    private String name;
    private LocalDate date;
    private Integer price;
    private Integer rating;

    @Enumerated(EnumType.STRING)
    private FoodType category;

    @Lob // 준비물
    private String materials;

    @Lob // 강의 설명
    private String descriptions;

    // 강의 제작자
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Qna> qnas = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CourseFile> courseFiles = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TimeTable> timeTables = new ArrayList<>();

    // JPA
    public void updateMember(Member member) {
        this.member = member;
    }

    public void addRegister(Register register) {
        registers.add(register);
        register.updateCourse(this);
    }

    public void deleteRegister(Register register) {
        registers.remove(register);
        register.updateCourse(null);
    }

    public void addLike (Like like) {
        likes.add(like);
        like.updateCourse(this);
    }

    public void deleteLike (Like like) {
        likes.remove(like);
        like.updateCourse(null);
    }

    public void addQna (Qna qna) {
        qnas.add(qna);
        qna.updateCourse(this);
    }

    public void deleteQna (Qna qna) {
        qnas.remove(qna);
        qna.updateCourse(null);
    }

    public void addReview (Review review) {
        reviews.add(review);
        review.updateCourse(this);
    }

    public void deleteReview (Review review) {
        reviews.remove(review);
        review.updateCourse(null);
    }

    public void addCourseFile (CourseFile courseFile) {
        courseFiles.add(courseFile);
        courseFile.updateFK(this);
    }

    public void deleteCourseFile (CourseFile courseFile) {
        courseFiles.remove(courseFile);
        courseFile.updateFK(null);
    }

    public void addTimeTable (TimeTable timeTable) {
        timeTables.add(timeTable);
        timeTable.updateCourse(this);
    }

    public void deleteTimeTable (TimeTable timeTable) {
        timeTables.remove(timeTable);
        timeTable.updateCourse(null);
    }
    /////////////////////////////////////////////
}
