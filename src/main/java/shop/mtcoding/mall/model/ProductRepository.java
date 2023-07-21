package shop.mtcoding.mall.model;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @Repository - 컴포넌트 스캔을 해준다.
 * - 알아서 new 해줌
 * - heap 메모리에 로딩된다.
 * - IoC 컨테이너에 들어감
 * - 필요할때 DI를 해서 사용 가능하다.
 */
@Repository
public class ProductRepository {

    @Autowired
    private EntityManager em; // 오브젝트 맵핑을 해준다.

    // @트랜잭션을 달지 않으면 DB에 커밋(저장)되지 않는다.
    // Insert, Update, Delete에는 트랜잭션을 달아줘야 함!!!
    @Transactional
    public void saveWithFk(String name, int price, int qty, int sellerId) {
        // 1. 쿼리 작성
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty, seller_id) values(:name, :price, :qty, :sellerId)");
        // 2. 변수 바인딩 : DB의 name "컬럼" 과 Java의 name "변수"를 매칭
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.setParameter("sellerId", sellerId);
        // 3. 전송
        query.executeUpdate();
    }

    // Product ID로 Join한 내용 조회 -> 1번 상품에 대한 판매자 목록
    public Product findByIdJoinSeller(int id) {
        // 1. 쿼리 작성
        Query query = em.createNativeQuery("select *\n" +
                "from product_tb pt\n" +
                "inner join seller_tb st\n" +
                "on pt.seller_id = st.id\n" +
                "where pt.id = :id", Product.class); // 둘다 Entity이므로 맵핑 가능!!

        // 2. 변수 바인딩 : DB의 id "컬럼" 과 Java의 id "변수"를 매칭
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();  // 한건이면 getSingleResult(), 여러건이면 getResultList()

        return product;
    }


    // 단위 테스트에서 사용하는 메서드
    public ProductDTO findByIdDTO(int id) {
        // 1. 쿼리 작성
        Query query = em.createNativeQuery("select id, name, price, qty, '설명' as des from product_tb where id = :id");
        // 2. 변수 바인딩
        query.setParameter("id", id);

        // QLRM 라이브러리 사용
        JpaResultMapper mapper = new JpaResultMapper();
        // 조회할 것이니 맵핑할 클래스를 적어준다 -> ProductDTO.class
        ProductDTO productDTO = mapper.uniqueResult(query, ProductDTO.class);

        return productDTO;
    }

    // @트랜잭션을 달지 않으면 DB에 커밋(저장)되지 않는다.
    // Insert, Update, Delete에는 트랜잭션을 달아줘야 함!!!
    @Transactional
    public void save(String name, int price, int qty) {
        // 1. 쿼리 작성
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        // 2. 변수 바인딩 : DB의 name "컬럼" 과 Java의 name "변수"를 매칭
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        // 3. 전송
        query.executeUpdate();
    }

    // 전체 목록 조회
    public List<Product> findAll() {
        // 1. 쿼리 작성
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productlist = query.getResultList();  // 한건이면 getSingleResult(), 여러건이면 getResultList()

        return productlist;
    }

    // id 사용해서 조회
    public Product findById(int id) {
        // 1. 쿼리 작성
        // :id는 쿼리문에서 메서드의 매개변수를 할당하는 표현
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
        // 2. 변수 바인딩 : DB의 id "컬럼" 과 Java의 id "변수"를 매칭
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();  // 한건이면 getSingleResult(), 여러건이면 getResultList()

        return product;
    }

    // id로 내용 삭제
    // @트랜잭션을 달지 않으면 DB에 커밋(저장)되지 않는다.
    // Insert, Update, Delete에는 트랜잭션을 달아줘야 함!!!
    @Transactional
    public void deleteById(int id) {
        // 1. 쿼리 작성
        // :id는 쿼리문에서 메서드의 매개변수를 할당하는 표현
        Query query = em.createNativeQuery("delete from product_tb where id = :id", Product.class);
        // 2. 변수 바인딩 : DB의 id "컬럼" 과 Java의 id "변수"를 매칭
        query.setParameter("id", id);
        // 3. 전송
        query.executeUpdate();
    }

    // update
    // @트랜잭션을 달지 않으면 DB에 커밋(저장)되지 않는다.
    // Insert, Update, Delete에는 트랜잭션을 달아줘야 함!!!
    @Transactional
    public void updateRepo(int id, String name, int price, int qty) {
        // 1. 쿼리 작성
        Query query = em.createNativeQuery("UPDATE product_tb SET name = :name, price = :price, qty = :qty where id = :id", Product.class);
        // 2. 변수 바인딩 : DB의 id "컬럼" 과 Java의 id "변수"를 매칭
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        // 3. 전송
        query.executeUpdate();
    }
}
