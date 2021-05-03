package creaming.domain.order;

import creaming.domain.course.Course;
import creaming.domain.delivery.Delivery;
import creaming.domain.member.Member;
import creaming.domain.orderdetail.OrderDetail;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "orders")
@Entity
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime date; // 주문 날짜
    private Integer totalPrice; // 주문 금액

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderDetail> orderDetails = new ArrayList<>();

    public void updateMember(Member member) { this.member = member; }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.updateOrder(this);
    }

    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
        orderDetail.updateOrder(this);
    }

}
