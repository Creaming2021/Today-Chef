package creaming.dto;

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
    public static class Response {
        private Long registerId;
        private Long courseId;
        private Long memberId;

        private CourseDto.SimpleResponse course;

        private Integer paidPrice; // register 의 price
        private LocalDateTime paidDate; // register 의 createdDate

        public Response(Register register) {
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
    public static class PostRequest {
        private Long courseId;
        private Integer paidPrice;

        public Register toEntity() {
            return Register.builder()
                    .price(this.paidPrice)
                    .build();
        }

    }

}
