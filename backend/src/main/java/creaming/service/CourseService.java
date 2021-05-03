package creaming.service;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.register.Register;
import creaming.domain.register.RegisterRepository;
import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {

    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final RegisterRepository registerRepository;

    // 모든 강의 페이징 처리 후 출력
    public Page<CourseDto.SimpleResponse> getCourseAll(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(CourseDto.SimpleResponse::new);
    }

    // 모든 강의 페이징 없이 출력
    public List<CourseDto.SimpleResponse> getCourseAll() {
        return courseRepository.findAll()
                .stream()
                .map(CourseDto.SimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 강의 하나 출력
    public CourseDto.DetailResponse getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .map(CourseDto.DetailResponse::new)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
    }

    // todo : 해당 강의의 수강생 출력
    public List<MemberDto.DayOfWeekResponse> getCourseStudents(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        List<Register> registers = registerRepository.findByCourse(course);
        return registers.stream()
                // todo : 이부분 register의 dayofWeeok를 뽑아내야함
                .map(Register::getMember)
                .map(MemberDto.DayOfWeekResponse::new)
                .collect(Collectors.toList());
    }

    // 강의 제작
    @Transactional
    public Long postCourse(CourseDto.PostRequest dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        // todo : timetable과 course 연결되어있음


        return 1L;
    }

    // 강의 수정
    @Transactional
    public void putCourse(Long courseId, CourseDto.PutRequest dto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        // todo : timetable과 course 연결되어있음
    }

    // 강의 삭제
    @Transactional
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        course.getMember().deleteCourse(course);
        courseRepository.delete(course);
    }


}
