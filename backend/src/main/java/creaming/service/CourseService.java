package creaming.service;

import creaming.domain.course.CourseRepository;
import creaming.dto.CourseDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<CourseDto.SimpleResponse> getCourseAll(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(CourseDto.SimpleResponse::new);
    }

    public CourseDto.DetailResponse getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .map(CourseDto.DetailResponse::new)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
    }


}
