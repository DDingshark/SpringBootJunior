package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;


// 엔티티 객체를 사용하기위해 리포지토리 인터베이스 추가.
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
}
