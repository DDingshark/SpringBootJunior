package com.springboot.test;
/*
        테스트 코드.
            -> 최근 스프링부트 애플리케이션을 개발하면서 테스트 코드로 로직을 확인하는 과정이 중요해짐.

        테스트 코드를 작성하는 이유
            1. 개발 과정에서 문제를 미리 발견할 수 있음.
                -> 미리 예상되는 문제를 테스트코드로 테스트 함으로써 문제상황을 방지.

            2. 래픽토링의 리스크가 줄어듦
                -> 개발 업테이트 중에 계속해서 코드를 추가할탠대.. 테스트코드의 존재만으로도 현저하게
                    불상사를 막을 수 있음.

            3. 직접 애플리케이션을 가동하는 것 보다 빠른 테스트 가능.
                -> 단위테스트가 가능하기에 빠른 테스트 가능

            4. 하나의 명세문세로서의 기능을 수행.
            5. 코드가 작성된 목적을 명확하게 표현가능.
                -> 협업시 동작 검증을 위해 작성된 테스트 코드를 애플리케이션의 코드와 비교해서 보면
                        빠른 이해가 가능
 */

/*
        단위 테스트 vs 통합 테스트.
            단위 테스트(Unit Test) : 애플리케이션의 개별 모듈을 독립적으로 테스트.
                    -> 개별 메소드 별로의 테스트하는 가장 작은 단위의 테스트.

            통합 테스트(Integration Test) : 애플리케이션을 구성하는 다양한 모듈을 결합해 전체적인 로직이
                            의도에 맞게 돌아가는지 확인.
                    -> 외부(네트워크.. DB...) 등등의 요인들까지 함꼐 테스트 함 -> 할때마다 비용이 크다는 문제 있음.

 */

/*
        테스트 코드 작성하지
            1. Given - When - Then Pattern
                    -> 테스트 주도 개발  에서 파생된 BDD(Behavior - Driven - Development : 행위 주도 개발)
                           를 통해 탄생한 테스트 접근 방식.

                Given : 테스트를 수행하기전에 테스트에 필요한 환경을 설정
                    -> 뭐.. 변수를 설정하거나, Mock 객체를 통헤 특정 상황에 대한 행동을 정의

                WHen : 테스트의 목적을 보여주는 단계
                        실제 테스트코드가 포함되며 테스트를 통한 결과값을 가져옴.

                Then : 테스트의 결과를 검증

                      -> 간단한 단위테스트에서는 잘 사용하지 않음 : 불필요하게 코드가 일어지는 단점이있음.
                            ->but : 하나의 명세문서의 역할을 수행하는데는 도움을 주니까... 뭐 알아서하세용...



            2.F.R.I.S.T 속성
                F.R.I.S.T 속성은  테스트 코드를 작성하는데 도움을 주는 5가지 속성.??규칙..??

                F(Fast,빠르게) : 테스트는 빠르게 수행되어야 한다. -> 테스트가 느리면 코드를 개선하는 작업이 느려져
                                코드품질이 떨어질 수 있음.

                I(Isolated,독립적,고릭접) : 하나의 테스트 코드는 목적으로 여기는 하나의 대상에서만 수행되어여한다.
                                -> 외부와 연관되어 있으면 뭐... 테스트가 당연히 잘 안되겠죵...?

                R(Repeatable,반복가능한) : 테스트는 어떠한 환경에서도 반복가능하게 작성해야한다.
                                -> 개발환경의 변화나, 네트워크 연결의 여부와 상관없이 수행되어야 한다.

                S(Self_Validating, 자가검증) : 테스트는 그 자체만으로 검증이 완료되어야한다.
                                -> 테스트가 성공했는지 실패 했는지 확인할 수 있는 코드와 함꼐 작성해야한다.
                                ->  음... 결과만 출력하고 이게 원하는건지 개발자가 직접 확인하면... 힘들겠다.. ㅠ

                T(Timely,즉시에) : 테스트 코드는 테스트하려는 애플리케이션 코드를 구현하기전에 완성되여야한다.
                                    너무 늦게 작성된 테스트코드는 정상적인 역할을 수행하기 어렵다.
                                    (테스트 주도 개발이 아니라면 상관 안하기도함)
 */


/*
    JUnit 을 활용한 테스트 코드 작성
        JAVA 진영에서 사용되는 대표적인 테스트 프레임워크
            -> 어노테이션 기반의 테스트 코드를 작성가능 -> 매우 편리!!

    JUnit 의 세부 묘듈
        1, JUnit Platform : JVM 에서 테스트를 시작하기 위한 뼈대
                -> 테스트를 발견하고 테스트 계획을 세우는 테스트엔진의 인터페이스 포함
        2. JUnit Jupiter : 테스트엔진 API 를 포함

        3. JUnit Vintage : JUnit3,4 에 대한 테스트 엔진 AIP 를 포함.

                => JUnit 은 하나의 Platform 모델을 기반으로 Jupiter 와 Vintage 구현체의 역할을 수행.
 */


import org.junit.jupiter.api.*;

/*
    실습 프로젝트에서는 : DAO 없이 세비스 레이어 에서 바로 리포지토리를 사용하는 구조로 수정하겟씁니ㅏㄷ.
            -> ProductServiceImpl , entity 수정해야합니다..
 */
public class TestLifeCycle  // << 요고 옆에 있는거 누르면 싹다 테스트 하는데 랜덤으로 실행
                                //   ++ 각 @Test 옆에 있는거 누르면 각각 실행두 가능합니다!
{
    @BeforeAll // 테스트를 시작하기전에 호출되는 메소드
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation");
        System.out.println();
    }

    @AfterAll // 테스트를 종료하면서 호출되는 메소드
    static void afterAll(){
        System.out.println("## AfterAll Annotation");
        System.out.println();
    }

    @BeforeEach // 테스트(각각) 을 시작하기 전에 호출되는 메소드
    void beforeEach(){
        System.out.println("## BeforeEach Annotation");
        System.out.println();
    }

    @AfterEach // 테스트(각각) 을 종료하고 호출되는 메소드 ( Each 는 보통 독립성을위해 clear 용도로도 사용)
    void afterEach(){
        System.out.println("## AfterEach Annotation");
        System.out.println();
    }

    @Test // 테스트 코드를 포함하는 메소드 정의 ( 각각 실행두 가능)
    void test(){
        System.out.println("## TEST 1 START");
        System.out.println("## TEST 1 ENDED");
    }

    @Test
    void test2()
    {
        System.out.println("## TEST 2 START");
        System.out.println("## TEST 2 ENDED");
    }

    @Test
    @Disabled //사용하지마!!
    void test3()
    {
        System.out.println("##TEST 3 START");
        System.out.println("##TEST 3 ENDED");
    }

}
