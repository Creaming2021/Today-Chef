package creaming.domain.file;

import creaming.domain.review.ProductReview;
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
public class ProductReviewFile extends File{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_review_id")
    private ProductReview productReview;

    @Builder
    public ProductReviewFile(String fileName) { super(fileName); }

    // JPA
    @Override
    public void updateFK(Object review) { this.productReview = (ProductReview) review; }
    /////////////////////////////////

}
