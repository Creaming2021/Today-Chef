package creaming.domain.course;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class CourseDescription {
    private String str;
//    private Info info;
//    private Thumbnail thumbnail;
//    private CourseDetail detail;
//    private Kit kit;
}

//class Info {
//    String categorySelected;
//    String categoryText;
//}

//class Thumbnail {
//    String className;
//}
//
//class CourseDetail {
//    String courseDetailEditor;
//}
//
//class Kit {
//    String kitEditor;
//}


