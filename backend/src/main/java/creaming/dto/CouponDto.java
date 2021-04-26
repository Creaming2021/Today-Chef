package creaming.dto;

import creaming.domain.membercoupon.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class CouponDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private UUID couponId;
        private String name;
        private String content;
        private Integer discount;
        private LocalDateTime expiredDate;
        private CouponStatus couponStatus;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private String name;
        private String content;
        private Integer discount;
        private Long expiredDay;
    }

}