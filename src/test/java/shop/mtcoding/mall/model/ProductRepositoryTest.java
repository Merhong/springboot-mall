package shop.mtcoding.mall.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

// T -> DS -> C -> Resp -> DB
// 현재 (Resp -> DB) 과정만 테스트 하고 싶음
@Import(ProductRepository.class)
@DataJpaTest // T -> DS -> C -> ( R -> DB )
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /* 단위 테스트 */

    // Test과정 : Given -> When -> Then
    @Test
    public void findByIdJoinSeller_test() {
        // Given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("사과", 5000, 50);

        // When (테스트 진행)
        Product product = productRepository.findById(1);

        // Then (테스트 확인)
        System.out.println("ID :" + product.getId());
        System.out.println("이름 :" + product.getName());
        System.out.println("가격 :" + product.getPrice());
        System.out.println("재고 :" + product.getQty());
    }

    @Test
    public void findByIdDTO_test() {
        // Given
        productRepository.save("사과", 5000, 50);

        // When
        ProductDTO productDTO = productRepository.findByIdDTO(1);

        // Then
        System.out.println("ID :" + productDTO.getId());
        System.out.println("이름 :" + productDTO.getName());
        System.out.println("가격 :" + productDTO.getPrice());
        System.out.println("재고 :" + productDTO.getQty());
        System.out.println("설명 :" + productDTO.getDes());
    }

    // Test과정 : Given -> When -> Then
    @Test
    public void findById_test() {
        // Given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("사과", 5000, 50);

        // When (테스트 진행)
        Product product = productRepository.findById(1);

        // Then (테스트 확인)
        System.out.println("ID :" + product.getId());
        System.out.println("이름 :" + product.getName());
        System.out.println("가격 :" + product.getPrice());
        System.out.println("재고 :" + product.getQty());
    }

    @Test
    public void findAll_test() {
        // Given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("딸기", 5000, 50);
        productRepository.save("바나나", 5000, 50);

        // When (테스트 진행)
        List<Product> productList = productRepository.findAll();

        // Then (테스트 확인)
        for (Product product : productList) {
            System.out.println("ID :" + product.getId());
            System.out.println("이름 :" + product.getName());
            System.out.println("가격 :" + product.getPrice());
            System.out.println("재고 :" + product.getQty());
            System.out.println("===================");
        }
    }
}

