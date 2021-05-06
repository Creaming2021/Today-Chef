package creaming.dto;

import creaming.domain.cart.Cart;
import creaming.domain.etc.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CartDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartResponse {
        private Long cartId;
        private Long productId;
        private String name;
        private Integer price;
        private FoodType category;
        private Integer amount;

        public CartResponse(Cart cart) {
            this.cartId = cart.getId();
            this.productId = cart.getProduct().getId();
            this.name = cart.getProduct().getName();
            this.price = cart.getProduct().getPrice();
            this.category = cart.getProduct().getCategory();
            this.amount = cart.getAmount();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartPostRequest {
        @NotNull
        private Long productId;
        @NotNull
        private Integer amount;

        public Cart toEntity() {
            return Cart.builder()
                    .amount(this.amount)
                    .build();
        }

    }


}
