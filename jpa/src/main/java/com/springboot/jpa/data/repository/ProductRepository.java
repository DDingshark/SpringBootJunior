package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


/*
    이 Repository 는 Spring Data JPA 가 제공하는 인터페이스입니당.
        ++ 쉽게 데이터 베이스를 사용하기위한 아키텍쳐를 제공받기위행.
            => JpaRepository 를 상속받음.


 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
/*
 Jpa Repository 에서 제동하는 기본 메소드들.
    List<T> findAll();
    List<T> findAll(Sort sort);
    List<T> findById(Iterable<ID> ids);
    <s extends T> List<S> saveAll(Iterable<S> entities);

    void flush();
    <S extends T> S saveAndFlush(S entity);
    <S extends T> List<S> saveAllAndFlush(Iterable<T> entities);

    @Deprecated
    default void deleteInBatch(Iterable<T> entities){
        this.deleteAllInBatch(entities);
    }

    void deleteAllinBatch(Iterable<T>entities);
    void deleteAllByIdInBatch(Iterable<ID> ids);
    void deleteAllInBatch();

    @Deprecated
    T getOne(ID id);
    T getById(Id id);
    <S extends T>List<S> findAll(Example<S> example);
    <S extends T>List<S> findAll(Example<S> example, Sort sort);

    뭐 이런거 제공 해줍니당..
 */

/*
        ++ Repository method 생성규칙

        FindBy -> SQL 문의 where 절 역할 -> findBy 뒤에 엔티티의 필드값을 입력

        AND OR -> 조건을 여려개 설정하기위해 사용

        Like/NotLike -> SQL 문의 like 와 동일한 기능을 수행 -> 특정 문자열 포함하는지 여부

        Starts(Ends)With/Starting(Ending)With ->특정 키워드로 시작하는(끝나는) 문자열 조건을 설정

        isNull /isNotNull -> 레코드 값이 Null 이거나 NUll 이 아닌 값을 검색

        True.False -> Boolean 타입의 레코드를 검색할때 사용

        Before/After -> 시간을 기준으로 값을 검색

        LessThan/GreaterThan -> 특정 값 기준으로 대소 비교를 할 때 사용

        Between -> 두 숫자 사이의 데이터를 조회

        OrderBy -> SQL 문에서 order by와 동일한 기능을 수행합니다.

        countBy -> SQL 문의 count 와 동일한 기능을 수행
 */