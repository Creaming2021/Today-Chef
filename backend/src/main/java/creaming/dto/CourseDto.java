package creaming.dto;

import creaming.domain.course.Course;
import creaming.domain.etc.FoodType;
import creaming.domain.file.CourseFile;
import creaming.domain.review.CourseReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public class CourseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseSimpleResponse {
        private Long courseId;
        private MemberDto.MemberSimpleProfile profile; // 강사 프로필
        private String name;
        private String date;
        private String time;
        private Integer price;
        private Double rating;
        private String image;
        private FoodType category;

        public CourseSimpleResponse(Course course) {
            this.courseId = course.getId();
            this.profile = new MemberDto.MemberSimpleProfile(course.getMember());
            this.name = course.getName();
            this.date = course.getDate();
            this.time = course.getTime();
            this.price = course.getPrice();
            this.rating = course.getCourseReviews().stream()
                    .collect(Collectors.averagingInt(CourseReview::getRating));
            List<CourseFile> courseFiles = course.getCourseFiles();
            if (!courseFiles.isEmpty()) {
                this.image = courseFiles.get(0).getFileName();
            }
            this.category = course.getCategory();
        }
    }

    @Getter
//    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseDetailResponse extends CourseSimpleResponse{

        private String materials;
        private String descriptions;

        public CourseDetailResponse(Course course) {
            super(course);
            this.materials = course.getMaterials();
            this.descriptions = course.getDescriptions();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoursePostRequest {
        @NotNull
        private Long memberId;
        @NotEmpty
        private String name;
        @NotEmpty
        private String date;
        @NotEmpty
        private String time;
        @NotNull
        private Integer price;
        @NotNull
        private FoodType category;
        @NotEmpty
        private String materials;
        @NotEmpty
        private String descriptions;

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
    public static class CoursePutRequest {
        @NotEmpty
        private Long courseId;
        @NotEmpty
        private String name;
        @NotEmpty
        private String date;
        @NotEmpty
        private String time;
        @NotEmpty
        private Integer price;
        @NotEmpty
        private FoodType category;
        @NotEmpty
        private String materials;
        @NotEmpty
        private String descriptions;
    }

}
