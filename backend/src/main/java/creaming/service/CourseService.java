package creaming.service;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.etc.FoodType;
import creaming.domain.file.CourseFile;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.product.Product;
import creaming.domain.product.ProductRepository;
import creaming.domain.register.Register;
import creaming.domain.register.RegisterRepository;
import creaming.dto.CourseDto;
import creaming.dto.MemberDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
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
    private final ProductRepository productRepository;

    // 모든 강의 페이징 처리 후 출력
//    public Page<CourseDto.CourseSimpleResponse> getCourseAll(Pageable pageable) {
//        return courseRepository.findAll(pageable)
//                .map(CourseDto.CourseSimpleResponse::new);
//    }

    // 모든 강의 페이징 없이 출력
    public List<CourseDto.CourseSimpleResponse> getCourseAll() {
        return courseRepository.findAll()
                .stream()
                .map(CourseDto.CourseSimpleResponse::new)
                .collect(Collectors.toList());
    }

    // 강의 하나 출력
    public CourseDto.CourseDetailResponse getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .map(CourseDto.CourseDetailResponse::new)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
    }

    // 해당 강의의 수강생 출력
    public List<MemberDto.MemberStudentResponse> getCourseStudents(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        List<Register> registers = registerRepository.findByCourseId(courseId);
        return registers.stream()
                .map(Register::getMember)
                .map(MemberDto.MemberStudentResponse::new)
                .collect(Collectors.toList());
    }

    // 강의 제작
    @Transactional
    public Long postCourse(CourseDto.CoursePostRequest dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        Course course = dto.toEntity();

        // 이미지
        List<String> images = dto.getImages();
        images.stream().filter(image -> !image.equals(""))
                .forEach(image -> course.addCourseFile(new CourseFile((image))));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        product.addCourse(course);
        member.addCourse(course);
        return courseRepository.save(course).getId();
    }

    // 강의 수정
    @Transactional
    public void putCourse(Long courseId, CourseDto.CoursePutRequest dto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        course.update(dto);
    }

    // 강의 삭제
    @Transactional
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new BaseException(ErrorCode.COURSE_NOT_FOUND));
        course.getMember().deleteCourse(course);
        courseRepository.delete(course);
    }

    public List<CourseDto.CourseSimpleResponse> getCourseRankByRating(int count, FoodType category) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getCategory().equals(category))
                .map(CourseDto.CourseSimpleResponse::new)
                .sorted((o1, o2) -> (int) (o2.getRating() - o1.getRating()))
                .limit(count)
                .collect(Collectors.toList());
    }

}
