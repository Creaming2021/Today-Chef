package creaming.domain.file;

import creaming.domain.etc.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class File extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    protected Long id;

    protected String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public abstract void updateFK(Object o);
}
