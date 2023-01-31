package com.springboot.advanced_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
/*
    JAP Auditing 사용하기

        JAP -> Aduit -> 감시하다.
        각 데이터 마다 "누가" "언제" 데이터를 생성했고 변경했는지 감시하는의미

        주로
        생성 주체
        생성 일자
        변경 주체
        변경 일자
        정도 필드를 넣는뎅... JAP에서는 자동으로 넣어주는 것이와용...

 */
public class AdvancedJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedJpaApplication.class, args);
    }


}
