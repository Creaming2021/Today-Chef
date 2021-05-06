package creaming.dto;

import creaming.domain.delivery.Delivery;
import creaming.domain.etc.Address;
import creaming.domain.order.Order;
import creaming.domain.orderdetail.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostOrderRequest {
        private Long memberId;
        private LocalDateTime orderDate;
        private Integer totalPrice;
        private List<OrderDetailDto.OrderDetailRequest> orderDetails;
        private DeliveryDto.DeliveryRequest delivery;

        public Order toEntity() {
            return Order.builder()
                    .date(orderDate)
                    .totalPrice(totalPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderResponse {
        private Long orderId;
        private LocalDateTime date;
        private Integer totalPrice;
        private DeliveryDto.DeliveryResponse delivery;
        private List<OrderDetailDto.OrderDetailResponse> orderDetails;

        public OrderResponse (Order order) {
            this.orderId = order.getId();
            this.date = order.getDate();
            this.totalPrice = order.getTotalPrice();
            this.delivery = new DeliveryDto.DeliveryResponse(order.getDelivery());
            this.orderDetails = order.getOrderDetails().stream()
                    .map(OrderDetailDto.OrderDetailResponse::new)
                    .collect(Collectors.toList());
        }
    }

}
