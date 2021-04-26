package creaming.domain.timetable;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.etc.FoodType;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TimeTableTest {

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

//    @BeforeEach
//    public void before() {
//
//        Member teacher = Member.builder()
//                .nickname("teacher")
//                .email("teacher@teacher.com")
//                .build();
//        memberRepository.save(teacher);
//
//        Course course = Course.builder()
//                .category(FoodType.DRINK)
//                .date(LocalDateTime.now().plusDays(4))
//                .price(10000)
//                .materials("밀가루 1kg, 설탕 100g")
//                .descriptions("정말 재미있는 클래스 입니다.")
//                .name("빵 만들기")
//                .member(teacher)
//                .build();
//        Course savedCourse = courseRepository.save(course);
//
//        // 타임 테이블 만듬
//        // 월 수 금 2시부터 5시전까지
//        // TODO : 시간 처리 어떻게 받는지? ex) 4시 59분 vs 5시
//        LocalTime startTime = LocalTime.of(02, 00, 00);
//        LocalTime endTime = LocalTime.of(04, 59, 59);
//
//        DayOfWeek[] dayOfWeeks = {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY};
//
//        for(DayOfWeek dayOfWeek : dayOfWeeks) {
//            TimeTable timeTable = TimeTable.builder()
//                    .dayOfWeek(dayOfWeek).startTime(startTime).endTime(endTime).course(course).build();
//            timeTableRepository.save(timeTable);
//        }
//
//        em.flush();
//        em.clear();
//    }

    @Test
    public void 강의_타임테이블_삽입() {

        Course findCourse = courseRepository.findAll().get(0);
        Assertions.assertThat(findCourse.getTimeTables().size()).as("강의 3개").isEqualTo(3);
        for(TimeTable t : findCourse.getTimeTables()) {
            System.out.println(" ========== " );
            System.out.println("요일 = " + t.getDayOfWeek());
            System.out.println("시작 시간 = " + t.getStartTime());
            System.out.println("종료 시간 = " + t.getEndTime());
        }

    }

    @Test
    public void 강의_타임테이블_수정() {

        Member teacher2 = Member.builder()
                .nickname("teacher")
                .email("teacher@teacher.com")
                .build();
        memberRepository.save(teacher2);

        Course course2 = Course.builder()
                .category(FoodType.DRINK)
                .date(LocalDateTime.now().plusDays(4))
                .price(10000)
                .materials("밀가루 1kg, 설탕 100g")
                .descriptions("정말 재미있는 클래스 입니다.")
                .name("빵 만들기")
                .member(teacher2)
                .build();
        Course savedCourse = courseRepository.save(course2);

        // 타임 테이블 만듬
        // 월 수 금 2시부터 5시전까지
        // TODO : 시간 처리 어떻게 받는지? ex) 4시 59분 vs 5시
        LocalTime startTime2 = LocalTime.of(02, 00, 00);
        LocalTime endTime2 = LocalTime.of(04, 59, 59);

        DayOfWeek[] dayOfWeeks2 = {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY};

        for (DayOfWeek dayOfWeek : dayOfWeeks2) {
            TimeTable timeTable = TimeTable.builder()
                    .dayOfWeek(dayOfWeek).startTime(startTime2).endTime(endTime2).course(course2).build();
            timeTableRepository.save(timeTable);
        }

        em.flush();
        em.clear();

        Course course = courseRepository.findAll().get(0);

        // TODO : 기존 타임 테이블 삭제
        List<TimeTable> timeTables = course.getTimeTables();
        timeTables.forEach(timeTable -> {
            System.out.println("timeTable.getClass() = " + timeTable.getClass());
            System.out.println("timeTable.getDayOfWeek() = " + timeTable.getDayOfWeek());
        });

//        TimeTable timeTable1 = timeTableRepository.findById(timeTables.get(0).getId()).get();

//        em.clear();

        TimeTable timeTable1 = timeTableRepository.findAll().get(0);
        course.test(timeTable1);
        timeTableRepository.delete(timeTable1);

//        timeTables.forEach(timeTable -> {
//            course.test(timeTable);
//            timeTableRepository.delete(timeTable);
//        });

        em.flush();
        em.clear();

        timeTableRepository.findAll().forEach(timeTable -> {
            System.out.println("timeTable.getCourse() = " + timeTable.getCourse());
            System.out.println("timeTable.getDayOfWeek() = " + timeTable.getDayOfWeek());
        });

        System.out.println("사이즈 : " + courseRepository.findAll().get(0).getTimeTables().size());

        // 새로 받는 타임 테이블
        LocalTime startTime = LocalTime.of(13, 00, 00);
        LocalTime endTime = LocalTime.of(16, 59, 59);

        DayOfWeek[] dayOfWeeks = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY};

        for(DayOfWeek dayOfWeek : dayOfWeeks) {
            TimeTable timeTable = TimeTable.builder()
                    .dayOfWeek(dayOfWeek).startTime(startTime).endTime(endTime).course(course).build();
            timeTableRepository.save(timeTable);
        }

        em.flush();
        em.clear();

        // 새로 저장된 타임 테이블 반영?
        Course findCourse = courseRepository.findAll().get(0);
        Assertions.assertThat(findCourse.getTimeTables().size()).as("강의 2개").isEqualTo(4);
        for(TimeTable t : findCourse.getTimeTables()) {
            System.out.println(" ========== " );
            System.out.println("요일 = " + t.getDayOfWeek());
            System.out.println("시작 시간 = " + t.getStartTime());
            System.out.println("종료 시간 = " + t.getEndTime());
        }

        timeTableRepository.findAll().forEach(timeTable -> {
            System.out.println("timeTable.getCourse() = " + timeTable.getCourse());
            System.out.println("timeTable.getDayOfWeek() = " + timeTable.getDayOfWeek());
        });


    }

    @Test
    public void 강의_삭제_후_타임테이블_조회() {

    }

    @Test
    public void LocalDateTime과_LocalTime의_비교() {

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = LocalTime.of(localDateTime.getHour(), localDateTime.plusMinutes(5).getMinute(), localDateTime.getSecond(), localDateTime.getNano());

        // LocalDateTime -> LocalTime
        // 1) localDateTime.toLocalTime()
        // 2) LocalTime.from(localDateTime)

        LocalTime localTime1 = localDateTime.toLocalTime();
        boolean result1 = localTime1.isBefore(localTime);
        Assertions.assertThat(result1).as("참1").isEqualTo(true);

        boolean result2 = localTime.isAfter(LocalTime.from(localDateTime));
        Assertions.assertThat(result2).as("참2").isEqualTo(true);

    }

}