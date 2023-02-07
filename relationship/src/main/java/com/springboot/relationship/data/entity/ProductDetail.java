package com.springboot.relationship.data.entity;

/*
    연관관계(Relationship)
        DB 수업에서 배웠듯이... 엔티티 하나로 모든것을 표현할 수 없음..
        E-R 다이어그램을 그리면 시각적으로 보이는것 처럼
        여려가지 엔티티들이 서로 관계를 맺으면서 DB에 저장이 되어있음.

        뭐.. 조인연산을 활용하는것두 특징 이니까...

        =>
         One To One     (1:1)
         One To Many    (1:N)
         Many To One    (N:1)
         Many To Many   (N:N)


         상품 Table   <N-1> 공급업체 Table
            이런 관계가 있을지도?

         상품 Table <1-1> 상품 정보 Table
            이런 관계가 있을지도

         혹은 매핑 방향에 따라서
         단방향 vs 양방향 이렇게 나눌수도 있음.

 */

/*
    Owner(주인)
        연관 관계가 설정되면 일반적으로 다른 테이블의 기본값을 외래키로 갖는당.
        보통 이럴때 갖는쪽이 관계의 주인이 되고.

        상대 엔티티는 읽기 작업만 수행할 수 있음.

 */

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long number;

    private String description;

    @OneToOne // 1:1 관계를 매핑하기 위한 어노테이션
    @JoinColumn(name = "product_number") // 매핑할 외래키를 설정(자동적으로 기본값이 들어가겠지만,
                                            // 원하는 칼럼명을 명명해주는게 보기도 좋고 나중에 좋음.
    /*
        추가 제약 조건
        name : 매핑할 외래키의 이름을 설정
        referencedColumnName : 외래키가 참조할 상대 테이블의 칼럼명을 지정
        foreignKey : 외래키를 생성하면서 지정할 제약조건을 설정 (unique,nullable,insertable,updatable,,,)
     */
    private Product product;

}
