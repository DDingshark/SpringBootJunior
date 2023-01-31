package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
// 굳이굳이굳이 @SpringBootTest 를 안쓰는이유 -> 걍... 느려셔ㅏㅏㅏ>..?
// 이것처럼 CRUD 뭐 테스트 가능하긴함.

public class ProductRepositoryTest2 {
    @Autowired
    ProductRepository productRepository;

    public void TestCRUD(){
        /*CREAT*/
        //given
        Product givenProduct =Product.builder()
                .name("pen")
                .price(1000)
                .stock(500)
                .build();

        //when
        Product savedProduct = productRepository.save(givenProduct);

        //then
        Assertions.assertThat(savedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());

        Assertions.assertThat(savedProduct.getName())
                .isEqualTo(givenProduct.getName());

        Assertions.assertThat(savedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());

        Assertions.assertThat(savedProduct.getStock())
                .isEqualTo(givenProduct.getStock());


        /*READ*/
        //given -> 한번만 쓰면댐

        //when
        Product selectProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        //then
        Assertions.assertThat(selectProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());

        Assertions.assertThat(selectProduct.getName())
                .isEqualTo(givenProduct.getName());

        Assertions.assertThat(selectProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());

        Assertions.assertThat(selectProduct.getStock())
                .isEqualTo(givenProduct.getStock());

        /*UPDATE*/
        //given

        //when
        Product foundProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        foundProduct.setName("PPEN");

        Product updatedProduct = productRepository.save(foundProduct);

        //then
        assertEquals(updatedProduct.getName(),"PPEN");

        /*DELETE*/
        //given

        //when
        productRepository.delete(updatedProduct);

        //then
        assertFalse(productRepository.findById(selectProduct.getNumber()).isPresent());
    }
}
