package creaming.service;

import creaming.domain.member.Member;
import creaming.domain.member.MemberRepository;
import creaming.domain.product.Product;
import creaming.domain.product.ProductRepository;
import creaming.domain.qna.ProductQna;
import creaming.domain.qna.ProductQnaRepository;
import creaming.dto.ProductQnaDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQnaService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductQnaRepository productQnaRepository;

    public List<ProductQnaDto.Response> getProductQnaList(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND))
                .getProductQnas().stream()
                .map(ProductQnaDto.Response::new)
                .collect(Collectors.toList());
    }

    public Long postProductQna(ProductQnaDto.PostRequest dto) {
        ProductQna productQna = productQnaRepository.save(dto.toEntity());

        memberRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)).addProductQna(productQna);

        productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)).addProductQna(productQna);

        return productQna.getId();
    }

    public Long putProductQna(Long id, ProductQnaDto.PutRequest dto) {
        ProductQna productQna = productQnaRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        productQna.update(dto);
        return productQna.getId();
    }

    public void deleteProductQna(Long id) {
        productQnaRepository.delete(productQnaRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)));
    }
}
