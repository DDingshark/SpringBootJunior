package com.springboot.jpa.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
/*
    사용하는 DB -> MariaDB

    ORM -> Object Relation Mapping : 객체 관게 매핑
        객체 : 클레스
        관계 : 관계형 데이터 베이스 (흔히 걍 DB라 부르는 것들) Relational DataBase

        -> 하지만.. 자바가.. DB랑 세세세 하라고 나온 친구가 아니자나요..?
          그래서 RDB table 과 불일치가 존재할수 밖에 없음

          1. 세분성(Granularity) : ORM 의 자동 설계 방법에 따라 DB 에 있는 테이블 수랑
                엔티티 클레스의 클래스 수가 다를수 있음.

          2. 상속성(Inheritance) : RDBMS 에는 상속따윈 없쪙

          3. 연관성(Association) : 객체지향 언어 에서는 객체르 참조함으로 연관성을 나타내지만,
                RDBMS에서는 외래키를 삽입 함으로써 연관성을 표현(DB설계할때 4단계였나 3단계였나 그럼)
                ++ RDBMS에서는 참조 방향성따윈 없응

           4. 탐색(Navigation) : 자바 -> 객체참조같은 방식으로 그래프 처럼 접급해서 탐색함
                                RDBMS ->  쿼리르 최소화 하고 조인(Join)을 통해 테이블을 로드하고 값을 추출
                                    -> join은 또보넹...

 */

/*
        JPA :
               뭐 이런저런 문제들이 있어성... 하지만... 기술의 발전은 항상 결핍에서
                JPA(JAVA Persistence API) : 자바 진영의 ORM 기술 표준으로 채택된 인터페이스 모음

                ORM 보다 저 구체화된 스펙을 포함합니당.

                Spring Boot                          Data
                    Application  <->  JPA(ORM)  <->     Base
                뭐 이런느낌의 메커니즘을 명시한 표준 명세
                        JPA -> EclipseLink, Hibernate, DataNucleus 뭐 대표적인 3가지 구현체 존재

                        여기선 Hibernate를 채택해서 사용하겠슴당

 */

/*
        Spring Data JPA
            Spring Data JPA 는 JAP를 더 편리하게 사용하는 스프링 하위 프로젝트 중에 하나.
                 CRUD 처리에 인터페이스를 제공하고,
                 하이버네이트의 엔티티 메니저를 직접 다루지않고 리포지토리를 정의해 사용해서
                 스프링이 적합한 쿼리를 동적으로 생성하는 방법으로 DB를 조작.

        Spring Boot                JPA                          Data
            Application <-> Hibernate(<->Spring DataJPA) <->        Base
 */

/*
        영속성 컨테스트 (Persistence Context)
                애플리케이션과 DB 사이에서 엔티티와 레코드의 괴리를 해소하는 기능과 객체를 보관하는 기능을 수핼
                    ( 아까 문제점 1번)

                Application        Persistence Context      DataBase
                    entity <--->    Persistence         <--->  Data
                    entity <--->       Object(영속객체)   <---> Data

                Entity Manager :
                    엔티티를 관리하는 객체 -> 데이터베이스에 접근해서 CRUD 작업을 수핼.
                        Spring Data JPA 를 사용하면 리포지토리를 사용해서 데이터 베이스에 접근
                        뭐 그렇슴당...

                        ++
                        Entity LifeCycle
                            비영속 (NEW) :
                                영속성 컨텍스트에 추가되지 않은 엔티티 객체상태
                            영속 (Managed) :
                                 영속성 컨텍스트에 의해 엔티티 객체가 관리되는 상태

                            준영속 (Detached) :
                                 영속성 컨텍스트에 의해 관리되던 엔티티 객체가 분리

                            삭제 (Removed)
                                 데이터베이스에서 레코드를 삭제하기위해 영속성 컨텍스트에 삭제요청


 */

/*
    엔티티(Entity 설계)
    Spring Data JPA -> Entity 를 사용하면 쿼리문 작성 없이 데이터베이스에 테이블을 생성하기 위해
        직접 쿼리를 작성할 필요가 없당.

    예를 들면 Product 엔티티 클레스로
    상품 테이블
    상품번호        int
    상품이름        varchar
    상품가격        int
    상품재고        int
    상품생성일자     DataTime
    상품정보변결일자  DataTime 뭐 이런 테이블을 만들기로 해보장...
 */


@Entity
    /*
        해당 클래스가 엔티티 클레스임을 명시하기 위한 어노테이션
            클래스 자체는 테이블과 1대1로 매핌되게 되며
            해당 클래스의 인스턴스는 매핑되는 테이블에서 하나의 레코드를 의미

     */
@Table(name = "product")
    /*
        특별한 경우가 아니하면 굳이굳이 Entity 어노테이션과 같이 쓰진X -> 1대1로 매칭되기에...
            클래스 이름과 테이블의 이름이 다를때 사용합니당.
     */
public class Product {

    @Id
    /*
        엔티티 클레스의 필드는 테이블레 칼럼과 매핑된다. -> 기본값역할로 사용
            **무조건 있어야함.. !!!!
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
        ID 어노테이션과 함께 사용 -> 필드의 값이 어떻게 자동으로 생설할지 결정.

        @GeneratedValue 를 사용하지 않는 방법도 있는댕(직접할당)
            -> 애플리케이션에서 자체적으로 교유한 기본값을 생성할 경우.
            -> 내부에 정해진 규칙에 의해 기본값을 생성하고 식별자로 사용

        @GeneratedValue(strategy=Generation.type.AUTO)
            -> @GeneratedValue 의 기본 설정값
            -> 기본 값을 사용하는 데이터 베이스에 맞게 자동 생성

        @GeneratedValue(strategy=Generation.type.IDENTITY)
            -> 기본값 생성을 데이터 베이스에 위임하는 방식
            -> 데이터 베이스의 AUTO_INCREMENT 를 사용해 기본값으로 생성
                    => String name -> varchar 형식으로 데이터 베이스에 저장됨.

        @GeneratedValue(strategy=Generation.type.SEQUENCE)
            ->@SequenceGenerator 어노테이션으로 식별자 생성기를 설정하고 이를 통해 값을 자동으로 주입받음
            추후에 사용할때 알아봅시당...
        @GeneratedValue(strategy=Generation.type.TABLE)
            ->어떤 DBMS 를 사용하더라도 동일하게 동작하기 원할경우에 사용
     */



    private Long number;

    @Column(nullable = false)
    private String name;
    /*
        @Column :
            ->필드의 여러가지 설정을 더할 때 사용합니당.
        뭐 여러가지 어노테이션 목록이 있는뎅... 대충
        public @interface Column{
            String name() default "";

            boolean unique() default true;

            boolean nullable() default true; // 디폴트가 TRUE 입니다!!!!!!

            boolean insertable() default true;

            boolean updatable() default true;

            String columnDefinition() default "";

            int length() default 255;

            int precision() default 0;

            int scale() default 0;

            // 학교 DB 수업에서 많이 봤던거... ㅋㅋ

            ++
            @Transient
                -> 엔티티 클레스에 성언되어있는 필드 이지만 DB 에서는 필요없을때
                    이용하지 않게 설정도 가능.
     */

    @Column(nullable =false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getStock() {
        return stock;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
