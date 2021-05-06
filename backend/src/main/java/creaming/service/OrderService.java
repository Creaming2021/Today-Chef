package creaming.service;

import creaming.domain.delivery.Delivery;
import creaming.domain.delivery.DeliveryRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.order.Order;
import creaming.domain.order.OrderRepository;
import creaming.domain.orderdetail.OrderDetail;
import creaming.domain.orderdetail.OrderDetailRepository;
import creaming.domain.product.Product;
import creaming.domain.product.ProductRepository;
import creaming.dto.OrderDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;

    public List<OrderDto.OrderResponse> getOrderList(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND))
                .getOrders().stream()
                .map(OrderDto.OrderResponse::new)
                .collect(Collectors.toList());
    }

    public Long postOrder(OrderDto.PostOrderRequest dto) {

        Order order = orderRepository.save(dto.toEntity());

        Delivery delivery = deliveryRepository.save(dto.getDelivery().toEntity());

        order.addDelivery(delivery);

        memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND))
                .addOrder(order);

        dto.getOrderDetails().forEach(postOrderDetail -> {
            Product product = productRepository.findById(postOrderDetail.getProductId())
                    .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

            OrderDetail orderDetail = orderDetailRepository.save(postOrderDetail.toEntity(product.getPrice()));

            product.addOrderDetail(orderDetail);
            order.addOrderDetail(orderDetail);
        });

        return order.getId();
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        orderRepository.delete(order);
    }
}
