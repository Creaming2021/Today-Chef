package creaming.domain.review;

import creaming.domain.comment.ReviewComment;
import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.file.CourseReviewFile;
import creaming.domain.member.Member;
import creaming.dto.CourseReviewDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_reviews")
@EqualsAndHashCode(of = "id", callSuper = false)
public class CourseReview extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_review_id")
    private Long id;

    private String title;
    private String content;
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "courseReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReviewComment> reviewComments = new ArrayList<>();

    @OneToMany(mappedBy = "courseReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CourseReviewFile> courseReviewFiles = new ArrayList<>();

    // JPA
    public void updateCourse(Course course) {
        this.course = course;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void addComment(ReviewComment reviewComment) {
        reviewComments.add(reviewComment);
        reviewComment.updateFK(this);
    }

    public void deleteComment(ReviewComment reviewComment) {
        reviewComments.remove(reviewComment);
        reviewComment.updateFK(null);
    }

    public void addCourseReviewFile(CourseReviewFile courseReviewFile) {
        courseReviewFiles.add(courseReviewFile);
        courseReviewFile.updateFK(this);
    }
    /////////////////////////////////////////

    public CourseReview(CourseReviewDto.CourseReviewPostRequest dto, Member member, Course course) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.rating = dto.getRating();
        member.addReview(this);
        course.addReview(this);
    }

    public void update(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

}
