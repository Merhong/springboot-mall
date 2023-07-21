package shop.mtcoding.mall.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository // 컴포넌트 스캔
public class SellerRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void save(String name, String email) {
        // 1. 쿼리 작성
        Query query = em.createNativeQuery("insert into seller_tb(name, email) values(:name, :email)");
        // 2. 변수 바인딩
        query.setParameter("name", name);
        query.setParameter("email", email);
        // 3. 전송
        query.executeUpdate();
    }
}
