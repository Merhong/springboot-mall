package shop.mtcoding.mall.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository // 컴포넌트 스캔해줌 -> 알아서 new가 된다. -> heap 메모리에 로딩된다.
public class ProductRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void save(String name, int price, int qty) {
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        query.setParameter("name", name);   // 바인딩
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();  // 전송
    }

    // 전체 목록 조회
    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productlist = query.getResultList();  // 한건이면 getSingleResult(), 여러건이면 getResultList()

        return productlist;
    }

    // id 사용해서 조회
    public Product findById(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class); // :id는 메서드의 매개변수 할당하는 표현
        query.setParameter("id", id);   // 변수 바인딩
        Product product = (Product) query.getSingleResult();  // 한건이면 getSingleResult(), 여러건이면 getResultList()
        return product;
    }
}
