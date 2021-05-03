package creaming.domain.orderdetail;

import creaming.domain.member.Member;
import creaming.domain.order.Order;
import creaming.domain.product.Product;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
@EqualsAndHashCode(of = "id", callSuper = false)
public class OrderDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    private Integer amount; // 수량
    private Integer price; // amount x product price

    // order check

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public void updateProduct(Product product) { this.product = product; }

    public void updateOrder(Order order) { this.order = order; }

}
