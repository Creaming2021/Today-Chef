package creaming.domain.event;

import creaming.domain.etc.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Entity
@Getter
@Table(name = "events")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "event_id")
    private UUID id;

    private String title;

    @Lob
    private String content;

    private String image;

}
