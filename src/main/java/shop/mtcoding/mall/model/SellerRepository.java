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
        Query query = em.createNativeQuery("insert into seller_tb(name, email) values(:name, :email)");
        query.setParameter("name", name);   // 바인딩
        query.setParameter("email", email);
        query.executeUpdate();  // 전송
    }
}
