package creaming.domain.file;

import creaming.domain.course.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseFile extends File{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder
    public CourseFile(String fileName) { super(fileName); }

    // JPA
    @Override
    public void updateFK(Object course) {
        this.course = (Course) course;
    }
    ////////////////////////////////
}
