package creaming.dto;

import creaming.domain.register.Register;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


public class RegisterDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterResponse {
        private Long registerId;
        private Long courseId;
        private Long memberId;

        private CourseDto.CourseSimpleResponse course;

        private Integer paidPrice; // register 의 price
        private LocalDateTime paidDate; // register 의 createdDate

        public RegisterResponse(Register register) {
            this.registerId = register.getId();
            this.courseId = register.getCourse().getId();
            this.memberId = register.getMember().getId();
            this.course = new CourseDto.CourseSimpleResponse(register.getCourse());
            this.paidPrice = register.getPrice();
            this.paidDate = register.getCreatedDate();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterPostRequest {
        @NotEmpty
        private Long courseId;
        @NotEmpty
        private Integer paidPrice;

        public Register toEntity() {
            return Register.builder()
                    .price(this.paidPrice)
                    .build();
        }

    }

}
