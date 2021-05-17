package creaming.domain.kakaopay;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Kakaopay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakaopay_id")
    private Long id;

    private String tid;
    private String url;
    private LocalDateTime created_at;
    private int amount;
}
