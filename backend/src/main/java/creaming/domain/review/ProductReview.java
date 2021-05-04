package creaming.domain.review;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.file.ProductReviewFile;
import creaming.domain.member.Member;
import creaming.domain.product.Product;
import creaming.dto.ProductReviewDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_reviews")
@EqualsAndHashCode(of = "id", callSuper = false)
public class ProductReview extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id")
    private Long id;

    private String title;
    private String content;
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductReviewFile> productReviewFiles = new ArrayList<>();

    // JPA
    public void updateProduct(Product product) {
        this.product = product;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public Long update(ProductReviewDto.ProductReviewPutRequest dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.rating = dto.getRating();
        return id;
    }
}
