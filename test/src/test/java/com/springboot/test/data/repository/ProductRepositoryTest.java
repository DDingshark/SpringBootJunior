package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Replace 속성의 기본값 -> Replace.ANY -> 임베디드 DB 사용
// -> NONE -> 본인(원래)DB 사용
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("테스트 데이터 베이스 변경 테스트")
    void save()
    {
        //given
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(1000);

        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertEquals(product.getName(),savedProduct.getName());
        assertEquals(product.getPrice(),savedProduct.getPrice());
        assertEquals(product.getStock(),savedProduct.getStock());
    }

}
