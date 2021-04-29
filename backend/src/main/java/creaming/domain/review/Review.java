package creaming.domain.review;

import creaming.domain.comment.QnaComment;
import creaming.domain.comment.ReviewComment;
import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.file.ReviewFile;
import creaming.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
@EqualsAndHashCode(of = "id", callSuper = false)
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String content;
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReviewComment> reviewComments = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReviewFile> reviewFiles = new ArrayList<>();

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
    /////////////////////////////////////////
}
