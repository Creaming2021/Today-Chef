package creaming.service;

import creaming.domain.coupon.CouponRepository;
import creaming.domain.qna.ProductQna;
import creaming.domain.qna.ProductQnaRepository;
import creaming.dto.AdminDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final ProductQnaRepository productQnaRepository;
    private final CouponRepository couponRepository;

    public void postProductQna(Long productQnaId, String answer) {
        ProductQna productQna = productQnaRepository.findById(productQnaId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        productQna.answerQna(answer);
    }

    public Long postCoupon(AdminDto.AdminCouponRequest dto) {
        return couponRepository.save(dto.toEntity()).getId();
    }
}
