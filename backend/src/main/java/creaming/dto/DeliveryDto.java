package creaming.dto;

import creaming.domain.delivery.Delivery;
import creaming.domain.etc.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DeliveryDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryRequest {
        private Address address;
        private String note;
        private String company;
        private String number;

        public Delivery toEntity() {
            return Delivery.builder()
                    .address(address)
                    .notes(note)
                    .company(company)
                    .number(number)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryResponse {
        private Address address;
        private String note;
        private String company;
        private String number;

        public DeliveryResponse(Delivery delivery) {
            this.address = delivery.getAddress();
            this.note = delivery.getNotes();
            this.company = delivery.getCompany();
            this.number = delivery.getNumber();
        }
    }
}
