package com.springboot.relationship.data.Repository;


import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.ProductDetail;
import com.springboot.relationship.data.repository.ProductDetailRepository;
import com.springboot.relationship.data.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;


    @Test
    @DisplayName("ProductDetailTest1")
    public void saveAndReadTest1(){
        Product product = new Product();
        product.setName("DDING JOO");
        product.setPrice(5000);
        product.setStock(100);
        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("DDING JOO REPOSITORY");
        productDetailRepository.save(productDetail);

        System.out.println("Saved Product : "+ productDetailRepository.findById(productDetail.getProduct().getNumber()));
        System.out.println("Saved Product Detail : "+ productDetailRepository.findById(productDetail.getNumber()));

        /*
        Hibernate:
    select
        productdet0_.number as number1_1_0_,
        productdet0_.created_at as created_2_1_0_,
        productdet0_.update_at as update_a3_1_0_,
        productdet0_.description as descript4_1_0_,
        productdet0_.product_number as product_5_1_0_,
        product1_.number as number1_0_1_,
        product1_.created_at as created_2_0_1_,
        product1_.update_at as update_a3_0_1_,
        product1_.name as name4_0_1_,
        product1_.price as price5_0_1_,
        product1_.stock as stock6_0_1_
    from
        product_detail productdet0_
    left outer join
        product product1_
            on productdet0_.product_number=product1_.number
    where
        productdet0_.number=?

    요로코롬 조회 할때 ProductDetail 이랑 Product 가 같이 조회가 되는것을 볼 수 있는뎅
        -> 엔티티를 조회할때 연관된 엔티티를 같이 조회하는것을 '즉시 로딩' 이라 한당

    ++
    left outer join 이 실행되는이유 => One To One 어노테이션 때문엥

    @OneToOne 어노테이션
        public @interface OneToOne{
            Class targetEntity() default void.class;

            CascadeType[] cascade() default {};

            FetchType fetch() default EAGER; <- 요고 즉시 로딩 전략 채택

            boolean optional() default true; <- 요고 매핑되는 값이 nullable 이라는 의미 입니당.
                // ProductDetail.java 로 가서 @OneToOne(optional = false) 요로케 하면 값이 무조건 있어야하게 설정가능.
                // ++ left outer join -> inner join 으로 바뀜.

            String mappedBy() default"";

            boolean orphanRemoval() default false;

         */
    }

}
