package creaming.domain.comment;

import creaming.domain.member.Member;
import creaming.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewComment extends Comment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewComment(UUID id, String content, Member member, Review review) {
        super(id, content, member);
        this.review = review;
    }

    // JPA
    @Override
    public void updateFK(Object review) {
        this.review = (Review) review;
    }
    ////////////////////////////////////////
}
