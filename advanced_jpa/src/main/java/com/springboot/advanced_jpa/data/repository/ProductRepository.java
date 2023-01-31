package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {



/*
    JPQL -> JAP Query Language
        => JAP 에서 사용할 수 있는 쿼리.

        기본구조
        =>
            SELECT p FROM Product p WHERE p.number ?=1;
                   뭐 이런 엔티티와 엔티티 속성으로 나타냄.. 쿼리문하고 별반 차이가 없어서
                        어렵진 않을듯.

        => QUERY vs JPQL
            => SQL 에서는 테이블이나 칼럼의 이름을 사용하는 것과달리
            => 매핑된 엔티티의 이름과 필드이름을 사용한다느 차이???
 */

/*
    리포지토이에서의 쿼리 메서드 생성
        -> 쿼리 메서드는 크게
                동작을 결정하는 주제(Subject) 와 서울어(Predicate)로 구분한다.
                    => find --- by, exists --- by 와 같은 키워드로 주제를 정하며
                        -> by 로 서숳어의 시작을 나타내는 구분자 역할.
                            (검색, 정렬...)

                 ex)
                   List<Person> findByLastnameAndEmail(String lastName, String email);
                   리턴타입         주제 +서술어(속성) 형식의 메서드.

                   => 쿼리 메서드의 주제 키워드
                        find---By
                        read---By
                        get---By
                        query---By
                        search---By
                        stream---By
                        => 뭐 다 조회하는 키워드 이네용..
                        Optional<Product> findByName(String name);
                        List<Product> findAllByName(String name);
                        Product queryByNumber(Long number);

                        exists---By
                        => 특정 데이터가 존재하는지 검사(Boolean 타입으로 리턴)
                        boolean existsByNumber(Long number);

                        count---By
                        => 조회쿼리를 수행한 후 결과로 나온 레코드의 개수리턴
                        long countByName(String name);

                        delete---By, remove---By
                        => 삭제 쿼리를 수행 -> 리턴타입이 없거나 삭제한 회수 리턴.
                        void deleteByNumber(Long number);
                        long removeByName(String name);

                        ----First<number>---,---Top<number>---
                        => 쿼리를 통해 조회된 결과값의 갯수를 제한하는 키워드
                        (둘다 같은 기능을 수행함)
                            -> 주제 와 By 사이에 위치
                        => List<Product> findFirst100ByNumber(Long number);
                            -> 100개만 조회
                           List<Product>findTop5ByNumber(Ling number);
                            -> 5개만 조회
                            (단건 으로 조회는 <number> 삭제)


                  => 쿼리 메서드의 조건자 키워드

                        Is
                        => 값의 일치를 조건으로 사용하는 조건자 키워드
                            (Equals 와 동일한 기능)
                        Product findByNumberIs(Long number);

                        (Is)Not
                        => 값의 불일치를 조건으로 사용하는 조건자 키워드.
                        Product findByNumberIsNot(Long number);
                        Product findByNumberNot(Long number);

                        (Is)Null, (Is)NotNull
                        => 값이 NULL 인지 검사하는 키워드
                        List<Product> findByUpdateAtNull();
                        List<Product> findByUpdateAtIsNull();
                        List<Product> findByUpdateAtNotNull();
                        List<Product> findByUpdateAtIsNotNull();

                        (Is)True, (Is)False
                        Product findByActiveTrue();
                        Product findByActiveIsTrue();
                        Product findByActiveFalse();
                        Product findByActiveIsFalse();

                        And,Or
                        => 여러 조건을 묶을때사용
                        Product findByNumberAndName(Long number, String name);
                        Product findByNumberOrName(Long number, String name);

                        (Is)GreaterThan, (Is)LessThan, (IS)Between
                        => 숫자나 datetime 같은 칼럼을 대상으로한 비교연산에 사용할수있는 조건자 키워드.
                        (경계값을 추가하려면 Equal 키워드 추가)
                        List<Product> findByPriceIsGreaterThan(Long price);
                        List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

                        (Is)StartingWith(==StartsWith), (Is)EndingWith(==EndsWith),
                        (Is)ContainingWith(==Contains), (Is)Like
                        => 컬럼값에서 일부 일치 여부를 확인하는 조건자 키워드 (SQL 에서 %<- 요고역할)

                            (주의) -> Like 키워드를 사용하려면 전달하는 값에 %<- 명시적으로 입력 해야댐.
 */


/*
    정렬과 페이징 처리
        
        -> 절렬처리하기
            => 일반적인 쿼리문에서 ORDER BY
            (Asc : 오름 차순 , Desc : 내림차순)
            List<Product> findByNameOrderByNumberAsc(String name);
            
            List<Product> findByNameOrderByNumberDesc(String name);
            -> 요거 하이버네이트 로그
            Hibernate:
                select
                    product0_.number as number1_0_,
                    product0_.created_at as created_2_0,
                    product0_.name as name3_0_,
                    product0_.price as price4_0_,
                    product0_.stock as stock5_0_,
                    product0_.update_at updated_6_0_
                from
                    product product0;
                where
                    product0_.name=?
                order by
                    product0_.number src
                    
           주의 -> 정렬 할때는 And 나 Or 쓰지 않고 앞에서부터 이어서 씀
            List<Product> findByNameOrderByPriceAscStockDesc(String name);
                -> 요론 느낌.
            
            List<Product> findByName(String name, Sort sort)
                -> 가독성을 위해 요런 매개변수를 활용하기도함.
            productRepository.findByName("pen", Sort.by(Order.asc("price"), Order.desc("stock")));
                -> 요로케 요로케
 */

/*
    페이징 처리
        -> 페이징
            -> DB 에서 레코드를 개수로 나눠 페이지를 구분하는 것을 의미

        -> JPA 에서는 페이징 처리를 위해 Page 와 Pageable 을 사용함
        Page<Product> Product = productRepository.findByName("pen",pageRequest.of(0,2));
            => pageable 을 사용하기위해 PageRequest 클래스 사용 -> pageable 의 구현체 이기에....
        Page<Product> findByName(String name,Pageable pageable);

        of(int page, int size) -> 페이지번호, 페이지당 데이터 개수
        of(int page, int size Sort) -> 페이지 번호, 페이지당 데잍 개수, 정렬
        of(int page, int  size, Direction, String ... properties)
            -> 페이지 번호, 페이지당 데이터 개수, 정렬방향, 속성(칼럼)
 */

/*
        @Query 어노테이션 사용하기
            -> DB 에서 데이터를 가져올때는 메소드의 이름으로 쿼리메서드를 생성할 수 있었당.
            -> 만약, DB를 다른 DB로 변경할 일이 없다면, 그 DB에 특화된 SQL 을 작성할 수 있으며
                주로 튜닝된 쿼리를 사용하고자 할때 SQL 을 직접 작성한다.

 */
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    List<Product> findByName(String name);
    // ?1 -> 파라미터를 전달받기위한 인자 (1 = 첫번째 파라미터를 의미
            // => 이렇게 작성하면 파라미터의 순서가 바뀌면 오류가 발생할 수 있기에 @Param 사용 추천
    @Query("SELECT p FROM Product p WHERE  p.name = :name")
    List<Product> findByNameParam(@Param("name")String name);
        // => 둘다 같은 하이버네이트를 써준당.
    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name = : name")
    List<Object[]> findByNameParam2(@Param("name")String name);
    // 요로코롬 원하는 칼럼의 값만 추출할수도 있음.
}