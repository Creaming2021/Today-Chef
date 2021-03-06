package creaming.domain.qna;

import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.member.Member;
import creaming.domain.product.Product;
import creaming.dto.ProductQnaDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_qnas")
@EqualsAndHashCode(of = "id", callSuper = false)
public class ProductQna extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_qna_id")
    private Long id;

    private String title;

    @Lob
    private String content;
    private Boolean isSecret;
    private String answer; // 답변
    private LocalDateTime answerDate; // 답변 일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
    // comments 없음
    
    // JPA
    public void updateProduct(Product product) { this.product = product; }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void update(ProductQnaDto.ProductQnaPutRequest dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.isSecret = dto.getIsSecret();
    }

    public void answerQna(String answer) {
        this.answer = answer;
        this.answerDate = LocalDateTime.now();
    }
}
