package creaming.domain.notice;

import creaming.domain.etc.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Entity
@Getter
@Table(name = "notices")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private UUID id;

    private String title;

    @Lob
    private String content;

    private String image;

}
