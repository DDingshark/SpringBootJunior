package com.springboot.advanced_jpa.service;

import com.springboot.advanced_jpa.data.dto.ProductDto;
import com.springboot.advanced_jpa.data.dto.ProductResponseDto;


/*
        서비스 레이어에서는 도메인 모델(Domain Model)을 활용해 애플리케이션에서 제공하는
                핵심 기능(세부 기능의 집합)을 제공

        이러한 모든 로직을 서비스레이어에서 포함하기는 쉽지않음
                -> 아키텍쳐를 서비스로직과 비즈니스 로직을 분리해 쓰기도 함.

                비즈니스 레이어 -> 세부기능 구현
                서비스  레이어 -> 세부기능을 모아 핵심기능을 전달
 */




public interface ProductService {

    ProductResponseDto getProduct(Long number);
    // DAO 에서 구현한 기능을 서비스 인터페이스에서 호출해 결과값을 가져오는 작업을 수행.
    /*
        DTO 객체를 리턴하는 것을 보면 => 서비스 레이어에서 DTO 객체와 엔티티 객체를 각 레이어에
            변환해서 전달하는 역할도 수행.


        클라이언트 <-(DTO)-> 컨트롤러 <-(DTO)-> 서비스 <-(Entity)-> DAO(Repository)<-(Entity)-> DB

        요런 느낌.
        ++ 서비스 <-> DAO 는 DTO 를 사용하기도함 -> 개발하는 팀에 맞쳐서
        ++ 단일이나 소량 데이터 이동에 대해서는 DTO 나 엔티티를 사용하지 않기도함
     */
    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
