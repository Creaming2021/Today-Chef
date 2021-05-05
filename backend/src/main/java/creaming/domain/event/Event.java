package creaming.domain.event;

import creaming.domain.etc.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Builder
@Entity
@Getter
@Table(name = "events")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    private String image;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateImage(String image) {
        this.image = image;
    }
}
