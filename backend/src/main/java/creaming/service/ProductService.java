package creaming.service;

import creaming.domain.product.Product;
import creaming.domain.product.ProductRepository;
import creaming.dto.ProductDto;
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
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto.ProductSimpleResponse> getProductList() {
        return productRepository.findAll().stream()
                .map(ProductDto.ProductSimpleResponse::new)
                .collect(Collectors.toList());
    }

    public ProductDto.ProductDetailResponse getProductDetail(Long id) {
        return new ProductDto.ProductDetailResponse(productRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)));
    }

    public void deleteProduct(Long id) {
        productRepository.delete(productRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)));
    }

    public Long postProduct(ProductDto.ProductRequest dto) {
        return productRepository.save(dto.toEntity()).getId();
    }

    public Long putProduct(Long id, ProductDto.ProductRequest dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        product.update(dto);
        return product.getId();
    }
}
