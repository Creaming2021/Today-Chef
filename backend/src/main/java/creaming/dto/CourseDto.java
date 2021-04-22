package creaming.dto;

import creaming.domain.etc.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class CourseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        private UUID courseId;
        private MemberDto.SimpleProfile profile; // 강사 프로필
        private String name;
        private LocalDateTime date;
        private Integer price;
        private Integer rating;
        private String image;
        private FoodType category;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimeTableResponse {


    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        private UUID memberId;

        private String name;
        private LocalDate date;
        private Integer price;
        private FoodType category;

        private String materials;
        private String descriptions;

        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        private UUID courseId;

        private String name;
        private LocalDate date;
        private Integer price;
        private String image;
        private FoodType category;

        private String materials;
        private String descriptions;

        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;
    }
}
