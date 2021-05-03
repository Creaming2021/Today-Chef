package creaming.dto;

import creaming.domain.course.Course;
import creaming.domain.etc.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class CourseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResponse {
        protected Long courseId;
        protected MemberDto.SimpleProfile profile; // 강사 프로필
        protected String name;
        protected LocalDate date;
        protected Integer price;
        protected Integer rating;
        protected FoodType category;
//        private String image;

        public SimpleResponse(Course course) {
            this.courseId = course.getId();
            this.profile = new MemberDto.SimpleProfile(course.getMember());
            this.name = course.getName();
            this.date = course.getDate();
            this.price = course.getPrice();
            this.rating = course.getRating();
            this.category = course.getCategory();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResponse extends SimpleResponse{

        private String materials;
        private String descriptions;

        public DetailResponse(Course course) {
            super(course);
            this.materials = course.getMaterials();
            this.descriptions = course.getDescriptions();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostRequest {
        @NotEmpty
        private Long memberId;
        @NotEmpty
        private String name;
        @NotEmpty
        private LocalDate date;
        @NotEmpty
        private Integer price;
        @NotEmpty
        private FoodType category;
        @NotEmpty
        private String materials;
        @NotEmpty
        private String descriptions;
        @NotEmpty
        private DayOfWeek dayOfWeek;
        @NotEmpty
        private LocalTime startTime;
        @NotEmpty
        private LocalTime endTime;

        public Course toEntity() {
            return Course.builder()
                    .name(this.name)
                    .date(this.date)
                    .price(this.price)
                    .category(this.category)
                    .materials(this.materials)
                    .descriptions(this.descriptions)
                    .build();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutRequest {
        @NotEmpty
        private Long courseId;
        @NotEmpty
        private String name;
        @NotEmpty
        private LocalDate date;
        @NotEmpty
        private Integer price;
        @NotEmpty
        private FoodType category;
        @NotEmpty
        private String materials;
        @NotEmpty
        private String descriptions;
        @NotEmpty
        private DayOfWeek dayOfWeek;
        @NotEmpty
        private LocalTime startTime;
        @NotEmpty
        private LocalTime endTime;
    }
}
