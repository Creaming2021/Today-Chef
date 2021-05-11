package creaming.domain.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRoomRepository extends JpaRepository<CourseRoom, Long> {

    public Optional<CourseRoom> findByToken(String token);
}
