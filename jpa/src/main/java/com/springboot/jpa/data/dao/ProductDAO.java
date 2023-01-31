package com.springboot.jpa.data.dao;
/*
    DAO(Data Access Object) :
        DB 에 접근하기위한 로직을 관리한다.
            -> 비즈니스의 로직의 동작 과정에서 데이터를 조작하는 기능은 DAO 객체가 담당
            -> 스프링 데이터 JPA 에서 DAO 개념은 리포지토리가 담당하고있음.

            => 규모가 작은 서비스에서는 DAO 를 설계하지 않고 바로 서비스 레이어에서 DB를 접근하기도 하지만..
                공부하는 중이기에...
                    DAO 를 서비스 레이어와 리포지토리의 중간 계층을 구성하는 역할로 활용

            => 규모가 있는 서비스에서는 중강계층을 두는게 유지보수 측면에서 용의
                물론 ) 서비스 -> DAO -> 리포지토리 -> DB 를 분리하기에 무조건 좋다 라고 하기는... 좀 그럼..

         객체지향적 관점에서...
                서비스와 비즈니스 레이어를 분리해서 각각의 레이어에서 각각의 로직을 수행해야 하는 의견도 있고...

         -> DAO vs Repository
                : 역할이 매우 비슷하기에 어떤 차이가 있다! 라기 애매한경우가 많음
                    실제로 리포지토리는 Spring Data JPA 에서 제공하는 기능이기에
                        기존의 스프링 프레임워크나 Spring MVC 를 사용하는 사람은 DAO Object -> DB 로 접근했음.


         -> 여기서는 서비스객체가 비즈니스 로직까지 포함하는 방향으로 설계하겠습니당.
                == 도메인(Entity)를 중심으로 다뤄지는 비즈니스로직으로 진행 하겠습니당.
 */

/*
    DAO 객체의 설계 :
        인터페이스 - 구현체 구성으로 사용
        의존성 결합을 낮추기 위한 디자인 패턴.
            => 서비스 레이어에 DAO 객체를 주입받을때 인터페이스를 선언하는 식으로 구성.

 */




import com.springboot.jpa.data.entity.Product;
public interface ProductDAO {

    //CRUD 를 다루기위한 메소드들
    Product insertProduct(Product product);
    // 알반적으로 DB 에 접근하는 메서드는 리턴값으로 데이터 객체를 전달
    // 이때 데이터 객체를 엔티티로 전달할지, DAO 로 전달할지는 개발자마다 다르댕...
    //  -> '일반적인' 설계에서는 엔티티객체는 DB 에 접근하는 계층에서만 사용하도록 정의함.
    //  ->  DAO 전달은 다른 계층으로 전달할때 사용한다.
    Product selectProduct(Long number);
    Product updateProductName(Long number, String name) throws  Exception;
    void deleteProduct(Long number) throws Exception;
}
