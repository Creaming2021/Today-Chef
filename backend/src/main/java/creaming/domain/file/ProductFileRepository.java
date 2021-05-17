package creaming.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {

    Optional<ProductFile> findFirstByProductId(Long productId);
}
