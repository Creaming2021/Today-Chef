package creaming.controller;

import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("courses")
public class CourseController {

    // 해당 강의의 수강생 출력
    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getCourseStudents(@PathVariable("courseId") Long courseId) {
        List<MemberDto.DayOfWeekResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 강의 제작
    @PostMapping
    public ResponseEntity<?> postCourse(CourseDto.PostRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    // 강의 이미지 삽입 및 수정
    @PostMapping("/{courseId}/image")
    public ResponseEntity<?> postCourseImage(@PathVariable("courseId") Long courseId,
                                             @RequestPart MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 강의 수정
    @PutMapping("/{courseId}")
    public ResponseEntity<?> putCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 강의 삭제 or 비활성화
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //


}
