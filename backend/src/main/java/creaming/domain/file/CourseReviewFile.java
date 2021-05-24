package creaming.domain.file;

import creaming.domain.review.CourseReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseReviewFile extends File{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_review_id")
    private CourseReview courseReview;

    @Builder
    public CourseReviewFile(String fileName) { super(fileName); }

    // JPA
    @Override
    public void updateFK(Object review) {
        this.courseReview = (CourseReview) review;
    }
    ///////////////////////////////////
}
