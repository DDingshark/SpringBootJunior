package com.springboot.relationship.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")




public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    // 1:1 양방향 매핑을위한 코드
    //@OneToOne
    //private ProductDetail productDetail;
    //
    //긍댕 이렇게하면 join이 두번 일어남. -> 너무 비효울작.
    // 주인 개념을 이용해 한쪽의 테이블에서만 외래키를 바꿀수 있게하면

    @OneToOne(mappedBy = "product")
    @ToString.Exclude
    private ProductDetail productDetail;
    // 이렇게하면 ProductDetail 이 Product 의 주인이 됩니당.
    /*
        ++ ToString 실행할때 StackOverFlowError 이 발생하는뎅..
            양방향 연관에서 ToString 을 사용하면 순환 참조가 발생함으로
            => 단방향으로 쓰던가
            @ToString.Exclude 추가

     */

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;


    @ManyToMany
    @ToString.Exclude
    // 다대다 양방향 매핑;

    private List<Producer> producers = new ArrayList<>();

    public void addProducer(Producer producer)
    {
        producers.add(producer);
    }


}
