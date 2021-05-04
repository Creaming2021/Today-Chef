package creaming.dto;

import creaming.domain.membercoupon.CouponStatus;
import creaming.domain.membercoupon.MemberCoupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


public class CouponDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long couponId;
        private String name;
        private String content;
        private Integer discount;
        private LocalDateTime expiredDate;
        private CouponStatus couponStatus;

        public Response(MemberCoupon memberCoupon) {
            this.couponId = memberCoupon.getCoupon().getId();
            this.name = memberCoupon.getCoupon().getName();
            this.content = memberCoupon.getCoupon().getContent();
            this.discount = memberCoupon.getCoupon().getDiscount();
            this.expiredDate = memberCoupon.getExpiredDate();
            this.couponStatus = memberCoupon.getCouponStatus();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        @NotEmpty
        private String name;
        @NotEmpty
        private String content;
        @NotEmpty
        private Integer discount;
        @NotEmpty
        private Long expiredDay;
    }

}
