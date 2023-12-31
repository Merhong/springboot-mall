package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "seller_tb") // 테이블명 설정
@Entity // 필드 분석후 테이블 제작
// 연관관계
public class Seller {
    @Id // 필드명을 보고 컬럼 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id에 auto_increment 추가
    private Integer id;

    private String name;
    private String email;
}
