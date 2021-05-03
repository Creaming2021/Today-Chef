package creaming.domain.cart;

import creaming.domain.course.Course;
import creaming.domain.member.Member;
import creaming.domain.product.Product;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "cart")
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private Integer amount; // 수량

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
