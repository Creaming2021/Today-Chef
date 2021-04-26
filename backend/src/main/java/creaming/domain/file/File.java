package creaming.domain.file;

import creaming.domain.etc.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "files")
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class File extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "file_id")
    private UUID id;

    private String fileName;

    public abstract void updateFK(Object o);
}
