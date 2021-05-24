package creaming.service;

import creaming.domain.member.MemberRepository;
import creaming.domain.product.ProductRepository;
import creaming.domain.review.ProductReview;
import creaming.domain.review.ProductReviewRepository;
import creaming.dto.ProductReviewDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public List<ProductReviewDto.ProductReviewResponse> getProductReviewList(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND))
                .getProductReviews().stream()
                .map(ProductReviewDto.ProductReviewResponse::new)
                .collect(Collectors.toList());

    }

    public Long postProductReview(ProductReviewDto.ProductReviewPostRequest dto) {
        ProductReview productReview = productReviewRepository.save(dto.toEntity());

        memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)).addProductReview(productReview);

        productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)).addProductReview(productReview);

        return productReview.getId();
    }

    public Long putProductReview(Long id, ProductReviewDto.ProductReviewPutRequest dto) {
        return productReviewRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND))
                .update(dto);
    }

    public void deleteProductReview(Long id) {
        productReviewRepository.delete(productReviewRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)));
    }

    public ProductReviewDto.ProductReviewResponse getProductReview(Long id) {
        return productReviewRepository.findById(id)
                .map(ProductReviewDto.ProductReviewResponse::new)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
    }
}
