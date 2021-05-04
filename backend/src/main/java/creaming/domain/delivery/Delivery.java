package creaming.domain.delivery;

import creaming.domain.etc.Address;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.order.Order;
import creaming.domain.register.Register;
import lombok.*;

import javax.persistence.*;


@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Delivery extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    private String notes; // 배송 요청 사항
    private String company; // 택배회사
    private String number; // 송장번호

    @OneToOne(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;

    public Delivery(Address address, String orderNotes) {
        this.address = address;
        this.notes = orderNotes;
    }

    // JPA
    public void updateOrder(Order order) { this.order = order; }
    /////////////////////////////////////////
}
