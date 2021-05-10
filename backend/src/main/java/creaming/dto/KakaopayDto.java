package creaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class KakaopayDto {

    @Data
    @AllArgsConstructor
    public static class KakaopayAddress {
        private String url;
    }

    @Data
    public static class KakaopayAmount {
        private Integer total, tax_free, vat, point, discount;
    }

    @Data
    public class KakaopayCard {

        private String purchase_corp, purchase_corp_code;
        private String issuer_corp, issuer_corp_code;
        private String bin, card_type, install_month, approved_id, card_mid;
        private String interest_free_install, card_item_code;
    }

    @Data
    public class KakaopayApproval {

        //response
        private String aid, tid, cid, sid;
        private String partner_order_id, partner_user_id, payment_method_type;
        private KakaopayAmount amount;
        private KakaopayCard card_info;
        private String item_name, item_code, payload;
        private Integer quantity, tax_free_amount, vat_amount;
        private LocalDateTime created_at, approved_at;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class KakaopayReady {

        private String tid;
        private String next_redirect_pc_url;
        private LocalDateTime created_at;
    }

}
