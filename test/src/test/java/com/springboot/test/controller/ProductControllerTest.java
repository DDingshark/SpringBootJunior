package com.springboot.test.controller;
/*
        스프링 부트에서의 TEST

            스프링 부트를 사용하는 애플리케이션이면 스프링부트가 제공하는 기능들을
                   사용하고 있기에 일부 모듈만 단위테스트 하기 힘든경우가 많음

                   -> 레이어 별로 사용하기 적합한 테스트 구성을 소개.


 */

/*
        Controller 객체의  TEST
            -> 컨트롤러 : 클라이언트 -> 서비스 컴포넌트로 요청전달 -> 결과를 클라이언트에게 전달.

            ** 우리가 만든 ProductController 은 ProductService 객체를 의존성 주입 받는다
                -> Controller 입장에서는 Service 도 외부 요인이기에
                -> Mock 객체를 활용해서 TEST CODE 를 작성해 보겠습니당.
 */

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





@WebMvcTest(ProductController.class)
    //WdbMvcTest ( 대상 class)
    // 웹에서 사용되는 요청과 응답에 사용할수 있음.
    // 대상을 설정하지 않으면 @RestController, @ControllerAdvice 들의 컨트롤러관련 빈 객체가 로드
    // @SpringBootTest 보다 가볍게 사용하기위해 사용

/*
    일반적으로 @WebMvcTest 를 사용한 테스트 슬라이스(Slice)테스라고 한다.
        -> 단위테스트와 통합테스트의 중간쯤 이라 생까하면 편한데.
        -> 아키텍쳐를 기준으로 각 레이어 별로 나눠서 테스트를 하는 의미.
            => 컨트롤러 특성상 웹과 맞닿은 레이어로써 외부요인을 차단하고 테스트하면 의미기 없기때문이당.
 */
public class ProductControllerTest{
    @Autowired
    private MockMvc mockMvc;
    // 서블릿 컨테이너 구동없이 가상의 MVC 환경에서 모의 HTTP 서블릿을 요청하는 유틸리티
    // 쭈ㅜㅜㅜㅜ욱 사용법이 나와있슴당.

    @MockBean
            //실제 빈객체가 아닌 Mock(가짜) 객체를 새성해서 주입하는 역할.
            // 실제로 행위를 수행하지 않기 때문에
            // 개발자가 직접 Mockito 의 given()메소드를 통해 동작을 정의해야댐.
    ProductServiceImpl productService;
    /*
        @MockBean 어노테이션을 통해 ProductController 가 의존성을 가지고 있던
            ProductService 객체에 Mock 객체를 주입
     */


    @Test
    @DisplayName("MockMvc 를 이용한 Product 데이터 가져오기 테스트")
    // Test 코드에서의 메소드 이름이 복잡해서 알아먹기 힘들때 쓰는 어노테이션
    void getProductTest() throws Exception{

        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L,"pen",5000,2000));
        // given 메소드를 통해 이 객체에 어떤 메서드가 호출되고 어떤 파라미터를 주입받는지 가정한 후
        //      willReturn 메소드로 어떤 결과를 리턴할지 정의하는 구조로 코드를 작성
        // ++ 그 given 의 역할 맞음 ㅇㅇ

        String productId = "123";

        mockMvc.perform(
                /*
                    perform() 메서드를 이용하면 서버로 URL 요청을 보내는 것 처럼 통신테스트 코드를 작성할 수 있음.
                    MockMvcRequestBuilder 에서 제공하는 HTTP 메소드로 URL 을 정의하여 사용
                    get, post ,delete,put 제공

                    ResultAction 객체가 결과로 리턴 -> andExpect 로 검증 수행가능.
                        -> andDo로 전체 내용 확인

                    When - Than 의 구조가 뭐 합쳐있는거 처럼 구성되어있슴.

                 */
                get("/product?number="+productId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.number").exists())
                        .andExpect(jsonPath("$.name").exists())
                        .andExpect(jsonPath("$.price").exists())
                        .andExpect(jsonPath("$.stock").exists())
                        .andDo(print());

        verify(productService).getProduct(123L);
        // 지정된 메서드가 실행 되었는지 검증 일반적으로 given 에 정의된 동작과 대응.

    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void creatProductTest() throws Exception{
        //Mock 객체에서 특정 메서드가 샐행되는 경우 실제 Return 을 받을수 없기에
        // 아래와 같이 가정사항을 만들어줌

        given(productService.saveProduct(new ProductDto("pen",5000,2000)))
                .willReturn(new ProductResponseDto(12315L,"pen",5000,2000));
        // given 메소드를 이용해 동작 구성을 설명.

        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();
        //ProductDto -> @Builder -> 롬북으로 추가해서 사용
        //테스트에 필요한 객체 생성.

        Gson gson = new Gson();
        //pom.xml 에서 의존성 추가 -> Google 에서 만든 JSON 파싱 라이브러리.
        String content = gson.toJson(productDto);

        mockMvc.perform(
                post("/product")
                        .content(content)
                        //@RequestBody 를 넘기기위해 content() 메서드에 DTO 값을 대입
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pen",5000,2000));
    }




}
