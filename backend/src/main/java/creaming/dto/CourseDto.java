package creaming.dto;

import creaming.domain.course.Course;
import creaming.domain.etc.FoodType;
import creaming.domain.file.CourseFile;
import creaming.domain.review.CourseReview;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public class CourseDto {

    @Setter
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
        private Integer reviewCnt;

        private String roomName;
        private String password;

        public CourseSimpleResponse(Course course) {
            this.courseId = course.getId();
            this.profile = new MemberDto.MemberSimpleProfile(course.getMember());
            this.name = course.getName();
            this.date = course.getDate();
            this.time = course.getTime();
            this.price = course.getPrice();
            this.rating = course.getCourseReviews().stream()
                    .collect(Collectors.averagingInt(CourseReview::getRating));
            this.image = course.getCourseFiles().get(0).getFileName();
            this.category = course.getCategory();
            this.reviewCnt = course.getCourseReviews().size();
            this.roomName = course.getRoomName();
            this.password = course.getPassword();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseDetailResponse {

        private Long courseId;
        private MemberDto.MemberSimpleProfile profile; // 강사 프로필
        private String name;
        private String date;
        private String time;
        private Integer price;
        private Double rating;
        private FoodType category;
        private String descriptions;
        private List<ImageDto> images;
        private ProductDto.ProductDetailResponse product;
        private String liveToken;
        private String roomName;
        private String password;

        public CourseDetailResponse(Course course) {
            this.courseId = course.getId();
            this.profile = new MemberDto.MemberSimpleProfile(course.getMember());
            this.name = course.getName();
            this.date = course.getDate();
            this.time = course.getTime();
            this.price = course.getPrice();
            this.rating = course.getCourseReviews().stream()
                    .collect(Collectors.averagingInt(CourseReview::getRating));
            this.descriptions = course.getDescriptions();
            this.category = course.getCategory();
            this.images = course.getCourseFiles().stream()
                    .map(courseFile -> new ImageDto(courseFile.getId(), courseFile.getFileName()))
                    .collect(Collectors.toList());
            if(course.getProduct() != null) {
                this.product = new ProductDto.ProductDetailResponse(course.getProduct());
            }
            this.liveToken = course.getCourseRoom().getToken();
            this.roomName = course.getRoomName();
            this.password = course.getPassword();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoursePostRequest {
        @NotNull
        private Long productId;
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
        private String descriptions;

        private String roomName;
        private String password;

        // 이미지 리스트 저장
        private List<String> images;

        public Course toEntity() {
            return Course.builder()
                    .name(this.name)
                    .date(this.date)
                    .price(this.price)
                    .category(this.category)
                    .descriptions(this.descriptions)
                    .time(this.time)
                    .roomName(this.roomName)
                    .password(this.password)
                    .build();
        }

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoursePutRequest {
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
    }

}
