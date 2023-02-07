package com.springboot.relationship.data.entity;

import lombok.*;
import javax.persistence.*;


import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "provider")
public class Provider extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String name;



    //영속성 전이
    /*
        영속성 전이(cascade) 란 특정엔티티의 영속성 상태르 변경할때
            그 엔티티와 연관괸 엔티티의 영속성에도 영향을 미쳐 영속성 상태를 변화시키는것.

         cascade = CascadeType. ddd

         ddd = >
            ALL : 모든 영속상태 변경에 대해 영속성 전이를 사용
            PERSIST : 엔티티가 영속화 할때 연관된 엔티티도 함계 영속화.
            MERGE : 엔티티를 영속성 컨텍스트에 병합할때 연관된 엔티티도 병합.
            REMOVE : 엔티티를 제거할때 연관된 엔티티도 제거
            REFRESH : 엔티티를 새로고침할때 연관된 엔티티도 새로고침
            DETACH : 엔티티를 연속성 컨텍스트에서 제외하면 같이 제외.

            => 생명주기를 잘 관리해서 사용하면.. GOOD;

     */
    @OneToMany(mappedBy = "provider",fetch = FetchType.EAGER,
                    cascade = CascadeType.PERSIST, //요런느낌으로 사용.
                    orphanRemoval = true) // 고아객체를 제거(고아객체 : 부모와의 연관이끊어진객체)
    //@OndToMany 의 default = Lazy -> 지연로딩 
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();

}
