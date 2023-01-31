package com.springboot.test.service.impl;


import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import com.springboot.test.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest(){
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest(){
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("pen");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        Assertions.assertEquals(productResponseDto.getNumber(),givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(),givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(),givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);

    }

    @Test
    void saveProductTest(){
        Mockito.when(productRepository.save(any(Product.class)))
                /*
                    any() ->
                        Mockito->ArgumentMatchers 에서 제공

                        Mock 객체의 동작을 정의하거 검증 하는 단계에서
                            조건으로 특정 매개변수의 전달을 설정하지 않고 메소드의 실행만을 확인하는 상황에서
                            사용

                 */
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("pen",1000,1234));

        Assertions.assertEquals(productResponseDto.getName(),"pen");
        Assertions.assertEquals(productResponseDto.getPrice(),1000);
        Assertions.assertEquals(productResponseDto.getStock(),1234);

        verify(productRepository).save(any());
    }
}
