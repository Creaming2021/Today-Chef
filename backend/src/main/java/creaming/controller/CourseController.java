package creaming.controller;

import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "모든 강의 페이징 처리 후 출력", description = "페이지 설정정보를 받아서 모든 강의를 출력합니다")
    @GetMapping
    public ResponseEntity<Page<CourseDto.SimpleResponse>> getCourseAll(Pageable pageable) {
        log.info("(Get) getCourseAll");
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseAll(pageable));
    }

    @Operation(summary = "강의 하나 출력", description = "courseId에 해당하는 강의 정보를 출력합니다")
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto.DetailResponse> getCourse(@PathVariable("courseId") Long courseId) {
        log.info("(Get) getCourse - courseId: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(courseId));
    }

    @Operation(summary = "해당 강의의 수강생 출력", description = "courseId에 해당하는 강의를 수강하는 member 출력")
    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getCourseStudents(@PathVariable("courseId") Long courseId) {
        log.info("(Get) getCourseStudents - courseId: {}", courseId);
        List<MemberDto.DayOfWeekResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "강의 제작", description = "강의를 제작합니다")
    @PostMapping
    public ResponseEntity<?> postCourse(@RequestBody @Valid CourseDto.PostRequest dto) {
        log.info("(Post) postCourse - memberId: {} | name: {} | date: {} | price: {} | category: {} | " +
                        "dayOfWeek: {} | startTime: {} | endTime: {}", dto.getMemberId(), dto.getName(), dto.getDate(),
                dto.getPrice(), dto.getCategory(), dto.getDayOfWeek(), dto.getStartTime(), dto.getEndTime());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO: 강의 이미지 추가 여부 확인
    @Operation(summary = "강의 이미지 추가", description = "강의 이미지를 수정, 삽입합니다.")
    @PostMapping("/{courseId}/image")
    public ResponseEntity<?> postCourseImage(@PathVariable("courseId") Long courseId,
                                             @RequestPart MultipartFile file) {
        log.info("(Post) postCourseImage - courseId: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "강의 수정", description = "해당 강의를 수정합니다.")
    @PutMapping("/{courseId}")
    public ResponseEntity<?> putCourse(@PathVariable("courseId") Long courseId,
                                       @RequestBody @Valid CourseDto.PutRequest dto) {
        log.info("(Put) postCourse - courseId: {} | name: {} | date: {} | price: {} | category: {} | " +
                        "dayOfWeek: {} | startTime: {} | endTime: {}", dto.getCourseId(), dto.getName(), dto.getDate(),
                dto.getPrice(), dto.getCategory(), dto.getDayOfWeek(), dto.getStartTime(), dto.getEndTime());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "강의 삭졔", description = "해당 강의를 삭제합니다.")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") Long courseId) {
        log.info("(Delete) deleteCourse - courseId: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
