package com.springboot.relationship.data.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
    다대다 매핑 ->
        다대다 연관관계에서는 각 엔티티에서 서로를 리스트로 가지는 구조가 만들어짐.
            -> 교차 엔티티라는 새로운 중간 테읍르을 생성해서 다대다 관계를
                1:N, N:1 관계로 풀어서 해소한당.
 */
/*
    N:M 단방향 매핑
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name ="producer")
public class Producer  extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;

    private String name;

    @ManyToMany
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }
}