package creaming.controller;

import creaming.domain.etc.FoodType;
import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

//    @Operation(summary = "모든 강의 페이징 처리 후 출력", description = "페이지 설정정보를 받아서 모든 강의를 출력합니다")
//    @GetMapping("")
//    public ResponseEntity<Page<CourseDto.CourseSimpleResponse>> getCourseAll(Pageable pageable) {
//        log.info("(Get) getCourseAll - Pageable");
//        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseAll(pageable));
//    }

    @Operation(summary = "모든 강의 페이징 없이 출력", description = "모든 강의를 출력합니다")
    @GetMapping
    public ResponseEntity<List<CourseDto.CourseSimpleResponse>> getCourseAll() {
        log.info("(Get) getCourseAll - No Pageable");
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseAll());
    }

    @Operation(summary = "강의 하나 출력", description = "courseId에 해당하는 강의 정보를 출력합니다")
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto.CourseDetailResponse> getCourse(@PathVariable("courseId") Long courseId) {
        log.info("(Get) getCourse - courseId: {}", courseId);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(courseId));
    }

    @Operation(summary = "해당 강의의 수강생 출력", description = "courseId에 해당하는 강의를 수강하는 member 출력")
    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<MemberDto.MemberStudentResponse>> getCourseStudents(@PathVariable("courseId") Long courseId) {
        log.info("(Get) getCourseStudents - courseId: {}", courseId);
        List<MemberDto.MemberStudentResponse> result = courseService.getCourseStudents(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "강의 제작", description = "강의를 제작합니다")
    @PostMapping
    public ResponseEntity<Long> postCourse(@RequestBody @Valid CourseDto.CoursePostRequest dto) {
        log.info("(Post) postCourse - memberId: {} | name: {} | date: {} | price: {} | category: {}",
                dto.getMemberId(), dto.getName(), dto.getDate(), dto.getPrice(), dto.getCategory());
        Long result = courseService.postCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "강의 수정", description = "해당 강의를 수정합니다.")
    @PutMapping("/{courseId}")
    public ResponseEntity<Void> putCourse(@PathVariable("courseId") Long courseId,
                                       @RequestBody @Valid CourseDto.CoursePutRequest dto) {
        log.info("(Put) postCourse - name: {} | date: {} | price: {} | category: {}",
                dto.getName(), dto.getDate(), dto.getPrice(), dto.getCategory());
        courseService.putCourse(courseId, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "강의 삭졔", description = "해당 강의를 삭제합니다.")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        log.info("(Delete) deleteCourse - courseId: {}", courseId);
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "카테고리별 top (count) 강의 리스트 요청", description = "카테고리별 상위권 강의를 count 개수만큼 가져옵니다.")
    @GetMapping("/rank")
    public ResponseEntity<List<CourseDto.CourseSimpleResponse>> getCourseRankByRating(@RequestParam("count") int count,
                                                                                      @RequestParam("category") FoodType category)  {
        log.info("(Get) getCourseRankByRating - count : {} | category : {}", count, category);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseRankByRating(count, category));
    }

}
