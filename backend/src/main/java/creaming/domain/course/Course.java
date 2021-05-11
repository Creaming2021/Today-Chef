package creaming.domain.course;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.etc.FoodType;
import creaming.domain.file.CourseFile;
import creaming.domain.like.Like;
import creaming.domain.member.Member;
import creaming.domain.product.Product;
import creaming.domain.qna.CourseQna;
import creaming.domain.register.Register;
import creaming.domain.review.CourseReview;
import creaming.domain.room.CourseRoom;
import creaming.domain.room.Room;
import creaming.dto.CourseDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Table(name = "courses")
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String name;
    private Integer price;
    private String date; // 강의 날짜
    private String time; // 강의 시간

    @Enumerated(EnumType.STRING)
    private FoodType category;

    @Lob // 준비물
    private String materials;

    @Lob // 강의 설명
    private String descriptions;

    @Embedded // 강의 내용...
    private CourseDescription courseDescription;

    // 강의 제작자
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CourseQna> courseQnas = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CourseReview> courseReviews = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CourseFile> courseFiles = new ArrayList<>();

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private CourseRoom courseRoom;

    // JPA
    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateProduct(Product product) { this.product = product; }

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

    public void addQna (CourseQna courseQna) {
        courseQnas.add(courseQna);
        courseQna.updateCourse(this);
    }

    public void deleteQna (CourseQna courseQna) {
        courseQnas.remove(courseQna);
        courseQna.updateCourse(null);
    }

    public void addReview (CourseReview courseReview) {
        courseReviews.add(courseReview);
        courseReview.updateCourse(this);
    }

    public void deleteReview (CourseReview courseReview) {
        courseReviews.remove(courseReview);
        courseReview.updateCourse(null);
    }

    public void addRoom(CourseRoom courseRoom) {
        this.courseRoom = courseRoom;
        courseRoom.updateCourse(this);
    }

    public void deleteRoom(CourseRoom courseRoom) {
        this.courseRoom = null;
        courseRoom.updateCourse(this);
    }

    public void addCourseFile (CourseFile courseFile) {
        courseFiles.add(courseFile);
        courseFile.updateFK(this);
    }

    public void deleteCourseFile (CourseFile courseFile) {
        courseFiles.remove(courseFile);
        courseFile.updateFK(null);
    }

    public void update(CourseDto.CoursePutRequest dto) {
        this.name = dto.getName();
        this.date = dto.getDate();
        this.time = dto.getTime();
        this.price = dto.getPrice();
        this.category = dto.getCategory();
        this.materials = dto.getMaterials();
        this.descriptions = dto.getDescriptions();
    }

    /////////////////////////////////////////////

}
