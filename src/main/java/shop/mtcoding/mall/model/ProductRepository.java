package shop.mtcoding.mall.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
/**
 * @Repository
 * - 컴포넌트 스캔을 해준다.
 * - 알아서 new 해줌
 * - heap 메모리에 로딩된다.
 * - IoC 컨테이너에 들어감
 * - 필요할때 DI를 해서 사용 가능하다.
 */
public class ProductRepository {

    @Autowired
    private EntityManager em;

    // @트랜잭션을 달지 않으면 DB에 커밋(저장)되지 않는다.
    // Insert, Update, Delete에는 트랜잭션을 달아줘야 함!!!
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
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class); // :id는 쿼리문에서 메서드의 매개변수를 할당하는 표현
        query.setParameter("id", id);   // 변수 바인딩
        Product product = (Product) query.getSingleResult();  // 한건이면 getSingleResult(), 여러건이면 getResultList()
        return product;
    }

    // id로 내용 삭제
    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from product_tb where id = :id", Product.class); // :id는 쿼리문에서 메서드의 매개변수를 할당하는 표현
        query.setParameter("id", id);   // 변수 바인딩 : id "컬럼" 과 id "변수"를 매칭
        query.executeUpdate();  // 전송
    }

    // update
    @Transactional
    public void updateRepo(int id, String name, int price, int qty) {
        Query query = em.createNativeQuery("UPDATE product_tb SET name = :name, price = :price, qty = :qty where id = :id", Product.class);
        query.setParameter("id", id);       // 변수 바인딩 : id "컬럼" 과 id "변수"를 매칭
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();  // 전송
    }
}
