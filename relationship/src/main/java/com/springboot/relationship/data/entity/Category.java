package com.springboot.relationship.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "catagory")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Code;

    private String name;


    //일대다 단방향 매핑

    @OneToMany(fetch =FetchType.EAGER)
    @JoinColumn(name ="catagory_id")
    private List<Product> products = new ArrayList<>();

}
