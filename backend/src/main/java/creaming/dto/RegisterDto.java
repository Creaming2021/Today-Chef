package creaming.dto;

import creaming.domain.etc.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private UUID registerId;
        private UUID courseId;
        private UUID memberId;

        private CourseDto.SimpleResponse course;

        private Integer paidPrice; // register 의 price
        private LocalDateTime paidDate; // register 의 createdDate
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse {
        private UUID registerId;
        private UUID courseId;
        private UUID memberId;

        private CourseDto.SimpleResponse course;

        private Integer paidPrice; // register 의 price
        private LocalDateTime paidDate; // register 의 createdDate

        private Address address;
        private String orderNotes;
        private String deliveryCompany;
        private String deliveryNumber;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private UUID courseId;
        private DayOfWeek dayOfWeek;

        private Integer paidPrice;

        private Address address;
        private String orderNotes;

    }




}
