package creaming.domain.comment;

import creaming.domain.member.Member;
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
@AllArgsConstructor
@NoArgsConstructor
public class ReviewComment extends Comment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_review_id")
    private CourseReview courseReview;

    @Builder
    public ReviewComment(String content) {
        super(content);
    }

    // JPA
    @Override
    public void updateMember(Member member) {
        this.member = member;
    }

    @Override
    public void updateFK(Object review) {
        this.courseReview = (CourseReview) review;
    }
    ////////////////////////////////////////

    public void update(String content) {
        this.content = content;
    }

}