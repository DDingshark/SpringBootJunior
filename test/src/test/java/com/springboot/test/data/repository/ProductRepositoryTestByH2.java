package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


/*
    리포지토리 객체의 테스트
        리포지토리는 개발자가 구현하는 레이어중에 DB 와 가장 가까움
        JpaRepository 를 상속받으면 기본 쿼리메소드를 구현가능
            -> 리포지토리 테스트는 구현하는 목적에대해 고민하고 작성해야댐.

        고려사항 ->
            1. fineById(), save() 와 같은 기본메서드의 테스틑 의미가 X
                -> 리포지토리의 기본 메서드는 이미 테스트가 완료된 코드

            2. 데이터 연동 여부 -> 굳이굳이 따지면 DB 는 외부요인 이긴함...
                    -> 굳이굳이 제외 할수도..?
                    -> 아니면 다른 DB 를 사용할지도    ->
                    -> 걍 DB를 사용해서 테스트 할지도  -> => 실제 저장이 된다는 거 고려해야댐.

        현제 사용 DB -> Maria DB : 이지만.? TEST 코드에서는 H2 DB 를 사용해서 구현하겠음
            -> pom 의존성 추가 + JpaRepository 에서 제공하는 기본메소드를 작성하면서 실습 예정.


 */
@DataJpaTest
/*
    JAP 와 관현된 설정만 로드해서 테스트를 진행

    기본적으로 @Transactional 어노테이션을 포함 -> 테스트가 끝나면 자동으로 DB 롤백

    기본값으로 임베디드 DB 사용 다른 DB를 쓰고싶으면 따로 설정을 해줘야댐.
 */
public class ProductRepositoryTestByH2 {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("데이터 베이스 저장 테스트")
    void saveTest()
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

    @Test
    @DisplayName("데이터 베이스 조회 테스트")
    void selectTest(){
        //given
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(product);

        //when
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        //then
        assertEquals(product.getName(),foundProduct.getName());
        assertEquals(product.getPrice(),foundProduct.getPrice());
        assertEquals(product.getStock(),foundProduct.getStock());



    }
}
