package creaming.domain.product;

import creaming.domain.cart.Cart;
import creaming.domain.course.Course;
import creaming.domain.etc.BaseTimeEntity;
import creaming.domain.etc.FoodType;
import creaming.domain.file.ProductFile;
import creaming.domain.like.ProductLike;
import creaming.domain.orderdetail.OrderDetail;
import creaming.domain.qna.ProductQna;
import creaming.domain.review.ProductReview;
import creaming.dto.ProductDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "products")
@Entity
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private Integer price;

    @Enumerated(EnumType.STRING)
    private FoodType category;

    @Lob
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductLike> productLikes = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductQna> productQnas = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductReview> productReviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductFile> productFiles = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderDetail> orderDetails = new ArrayList<>();

    // JPA
    public void addCourse(Course course) {
        courses.add(course);
        course.updateProduct(this);
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
        course.updateProduct(null);
    }

    public void addProductFile (ProductFile productFile) {
        productFiles.add(productFile);
        productFile.updateFK(this);
    }

    public void deleteProductFile (ProductFile productFile) {
        productFiles.remove(productFile);
        productFile.updateFK(null);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.updateProduct(this);
    }

    public void deleteCart(Cart cart) {
        carts.remove(cart);
        cart.updateProduct(null);
    }

    public void addProductLike(ProductLike productLike) {
        productLikes.add(productLike);
        productLike.updateProduct(this);
    }

    public void deleteProductLike(ProductLike productLike) {
        productLikes.remove(productLike);
        productLike.updateProduct(null);
    }

    public void addProductQna(ProductQna productQna) {
        productQnas.add(productQna);
        productQna.updateProduct(this);
    }

    public void deleteProductQna(ProductQna productQna) {
        productQnas.remove(productQna);
        productQna.updateProduct(null);
    }

    public void addProductReview(ProductReview productReview) {
        productReviews.add(productReview);
        productReview.updateProduct(this);
    }

    public void deleteProductReview(ProductReview productReview) {
        productReviews.remove(productReview);
        productReview.updateProduct(null);
    }

    public void update(ProductDto.ProductRequest dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.category = dto.getCategory();
        this.description = dto.getDescription();
    }


}
