package creaming.dto;

import creaming.domain.delivery.Delivery;
import creaming.domain.etc.Address;
import creaming.domain.register.Register;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class RegisterDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private Long registerId;
        private Long courseId;
        private Long memberId;

        private CourseDto.SimpleResponse course;

        private Integer paidPrice; // register 의 price
        private LocalDateTime paidDate; // register 의 createdDate

        public SimpleResponse(Register register) {
            this.registerId = register.getId();
            this.courseId = register.getCourse().getId();
            this.memberId = register.getMember().getId();
            this.course = new CourseDto.SimpleResponse(register.getCourse());
            this.paidPrice = register.getPrice();
            this.paidDate = register.getCreatedDate();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse {
        private Long registerId;
        private Long courseId;
        private Long memberId;

        private CourseDto.SimpleResponse course;

        private Integer paidPrice; // register 의 price
        private LocalDateTime paidDate; // register 의 createdDate

        private Address address;
        private String orderNotes;
        private String deliveryCompany;
        private String deliveryNumber;

        public DetailResponse(Register register) {
            // TODO : SimpleReponse와 동일한 코드 6줄,, SimpleResponse 쓰면 감싸서 보내게 됨
            this.registerId = register.getId();
            this.courseId = register.getCourse().getId();
            this.memberId = register.getMember().getId();
            this.course = new CourseDto.SimpleResponse(register.getCourse());
            this.paidPrice = register.getPrice();
            this.paidDate = register.getCreatedDate();
            this.address = register.getDelivery().getAddress();
            this.orderNotes = register.getDelivery().getNotes();
            this.deliveryCompany = register.getDelivery().getCompany();
            this.deliveryNumber = register.getDelivery().getNumber();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private Long courseId;
        private DayOfWeek dayOfWeek;

        private Integer paidPrice;

        private Address address;
        private String orderNotes;

        public Register toEntity() {
            return Register.builder()
                    .dayOfWeek(this.dayOfWeek)
                    .price(this.paidPrice)
                    .delivery(new Delivery(this.address, this.orderNotes))
                    .build();
        }

    }

}
