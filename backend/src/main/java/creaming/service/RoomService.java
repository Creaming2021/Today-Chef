package creaming.service;

import creaming.domain.course.Course;
import creaming.domain.course.CourseRepository;
import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.register.Register;
import creaming.domain.register.RegisterRepository;
import creaming.domain.room.CourseRoomRepository;
import creaming.dto.RoomDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final CourseRoomRepository courseRoomRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final RegisterRepository registerRepository;

    public RoomDto.RoomResponse joinRoom(Long userId, String token) {
        Course course = courseRoomRepository.findByToken(token)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND))
                .getCourse();

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        Register register = registerRepository.findByMemberAndCourse(member, course)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        return RoomDto.RoomResponse.builder()
                .nickname(member.getNickname())
                .isOwner(course.getMember().getId().equals(userId))
                .build();
    }
}
