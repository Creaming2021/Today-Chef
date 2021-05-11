package creaming.domain.room;

import creaming.domain.course.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRoom extends Room {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder
    public CourseRoom(String token) {
        super(token);
    }

    public void updateCourse(Course course) {
        this.course = course;
    }
}
