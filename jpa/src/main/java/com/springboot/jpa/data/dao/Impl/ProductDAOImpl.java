package com.springboot.jpa.data.dao.Impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
// ProductDAOImpl 클래스를 스프링이 관리하는 빈으로 등록하려고 하는 엔티티.
//          혹은 @Service
//          빈으로 등로된 객체는 다른 클레스가 인터페이스를 가지고 의존성을 주입받을때 이 구현체를 찾아 주입.
public class ProductDAOImpl implements ProductDAO {

    private  final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
    // 요로케 생성자를 설정하고 의존성 주입을 받는당.


    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getById(number);
        return selectedProduct;
    }
    // findById vs getByID
    //      getByID() -> 내부적으로 EntityManager 의 getReference() 메소드르 호출.
    //          => 이때 프락시 객체를 리턴 -> 이때 데이터가 존재하지 않을경우
    //              EntityNotFoundException 발생

    //SimpleJpaRepository 의 getById()
    //@Override
    //public T getNtId(ID id)
    //{
    //      Assert.notNull(id, ID_MUST_NOT_BE_NULL);
    //      return em.getReference(getDomainClass(),id);
    //} 이런식으로 서있습니당.

    //      fideById() -> 내부적으로 EntityManager 의 find() 메소드르 호출.
    //          => 이때 영속성 컨텍스트의 캐쉬에서 값을 조회 -> 영속성 컨텍스트에 존재하지않으면
    //               실제 데이터 베이스에서 값을 조회 ++ 리턴 값으로 Optional 객체

    //SimpleJpaRepository 의 findBtId()
    //    @Override
    //    public Optional<T> findBtId(ID id)
    //    {
    //        Assert.notNUll(id, ID_MUST_NOT_BE_NULL);
    //
    //        Class<T> domianType = getDomainClass();
    //
    //        if(metedata == null)
    //        {
    //            return Optional.ofNullable(em.find(domianType,id));
    //        }
    //
    //        LockModeType type = metadata.getLockModType();
    //
    //        Map<String,Object> hints = new HashMap<>();
    //        getQueryHints().withFetchGraphs(em).forEach(hints::put);
    //
    //        return Optional.ofNullable(type == null? en.find(domainType,id ,hints) :
    //        em.find(domianType,id,type,hints));
    //    }
    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);

        Product updatedProduct;
        if(selectProduct.isPresent())
        {
            Product product = selectProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        }

        else
        {
            throw new Exception();
        }

        return updatedProduct;
    }
    /*
        JPA 에서 데이터의 값을 갱신할때 update 라는 키워드를 사용하지 않음
            -> find 메소드를 통해 데이터베이스에서 값을 가져오면 가져온 객체가 영속석 컨텍스트에 추가
                    -> 영속성 컨텍스트가 유지되고있을때 객체의 값을 변경하고 save()를 샐행하면
                    -> JPA 에서는 더티체크(Duty Check)러고 하는 변경감지를 수행.
     */

    //SimpleJpaRepository 의 save() 메소드
    //    @Transactional
    //    public <S extends T> S save(S entity) {
    //        Assert.notNull(entity, "Entity must not be null.");
    //        if (this.entityInformation.isNew(entity)) {
    //            this.em.persist(entity);
    //            return entity;
    //        } else {
    //            return this.em.merge(entity);
    //        }
    //    }



    @Override
    public void deleteProduct(Long number) throws Exception {

        Optional<Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent())
        {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        }

        else
        {
            throw new Exception();
        }

    }
    /*
        JPA 에서 delete() 메소드로 전달받은 엔티티가 영속성 컨텍스트에 있는지 파악하고,
            -> 해당 엔티티를 영속성 컨텍스트에 영속화 하는 작업을 거쳐 DB 의 레코드와 매핑
            -> 매핑된 영속객체를 대상으로 삭제 요청을 수행하는 작업을 마치고 'commit' 단계에서 삭제 진행
     */

    //SimpleJpaRepository 의 delete 메소드
    //    @Transactional
    //    public void delete(T entity) {
    //        Assert.notNull(entity, "Entity must not be null!");
    //        if (!this.entityInformation.isNew(entity)) {
    //            Class<?> type = ProxyUtils.getUserClass(entity);
    //            T existing = this.em.find(type, this.entityInformation.getId(entity));
    //            if (existing != null) {
    //                this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
    //            }
    //        }
    //    }
}


