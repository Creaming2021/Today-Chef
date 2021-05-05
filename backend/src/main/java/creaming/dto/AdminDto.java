package creaming.dto;

import creaming.domain.coupon.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdminDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminProductQnaRequest {
        @NotEmpty
        private String answer;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminCouponRequest {
        @NotEmpty
        private String name;
        @NotEmpty
        private String content;
        @NotNull
        private Integer discount;
        @NotNull
        private Long expiredDay;

        public Coupon toEntity() {
            return Coupon.builder()
                    .name(name)
                    .content(content)
                    .discount(discount)
                    .expiredDay(expiredDay)
                    .build();
        }
    }
}
