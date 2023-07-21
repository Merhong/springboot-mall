package shop.mtcoding.mall.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String des; // 상품 설명

    // Lombok은 생성시 변수의 순서를 보장하지 않으므로 이런식으로 풀생성자를 만들고 builder를 달아 순서 오류를 방지한다.
    @Builder
    public ProductDTO(Integer id, String name, Integer price, Integer qty, String des) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.des = des;
    }
}
