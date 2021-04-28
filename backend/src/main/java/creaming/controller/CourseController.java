package creaming.controller;

import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("courses")
public class CourseController {

    // 해당 강의의 수강생 출력
    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getCourseStudents(@PathVariable("courseId") UUID courseId) {
        List<MemberDto.DayOfWeekResponse> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 강의 제작
    @PostMapping
    public ResponseEntity<?> postCourse(CourseDto.PostRequest dto) {
        UUID result = UUID.randomUUID();
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    // 강의 이미지 삽입 및 수정
    @PostMapping("/{courseId}/image")
    public ResponseEntity<?> postCourseImage(@PathVariable("courseId") UUID courseId,
                                             @RequestPart MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 강의 수정
    @PutMapping("/{courseId}")
    public ResponseEntity<?> putCourse(@PathVariable("courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 강의 삭제 or 비활성화
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
