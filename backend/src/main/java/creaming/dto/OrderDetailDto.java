package creaming.dto;

import creaming.domain.orderdetail.OrderDetail;
import creaming.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderDetailDto {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailRequest {
        private Long productId;
        private Integer amount;

        public OrderDetail toEntity(Integer price) {
            return OrderDetail.builder()
                    .amount(amount)
                    .price(amount * price)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailResponse {
        private ProductDto.ProductSimpleResponse product;
        private Integer amount;

        public OrderDetailResponse(OrderDetail orderDetail) {
            this.product = new ProductDto.ProductSimpleResponse(orderDetail.getProduct());
            this.amount = orderDetail.getAmount();
        }
    }
}
