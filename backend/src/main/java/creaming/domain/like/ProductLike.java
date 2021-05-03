package creaming.domain.like;

import creaming.domain.course.Course;
import creaming.domain.member.Member;
import creaming.domain.product.Product;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Table(name = "product_likes")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class ProductLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // JPA
    public void updateProduct(Product product) {
        this.product = product;
    }

    public void updateMember(Member member) {
        this.member = member;
    }
    /////////////////////////////////////

}
