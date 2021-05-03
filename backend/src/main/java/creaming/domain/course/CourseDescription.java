package creaming.domain.course;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class CourseDescription {
    private Info info;
    private Thumbnail thumbnail;
    private CourseDetail course;
    private Kit kit;
}

class Info {
    String categorySelected;
    String categoryText;
}

class Thumbnail {
    String className;
}

class CourseDetail {
    String editor;
}

class Kit {
    String editor;
}


