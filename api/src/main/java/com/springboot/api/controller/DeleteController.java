package com.springboot.api.controller;
// DELETE API
/*
    딱봐도 서버를 이용해 데베들에 있는 저장소에 있는 리소스를 삭제할때 사용
         (클라이언트 -> 리소스식별값 -> 리소스 조회 -> 삭제
 */

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
    //@PathVariable 과 @RequestParam 을 이용한 DELETE 메소드 구현.

    //http:localhost:8080/api/v1/delete-api/{String}
    @DeleteMapping(value = "/{variable}")
    public String DeleteVariable(@PathVariable String variable)
    {
        return variable;
    }

    // 혿은 RequestParam 을 활용하면 쿼리스트링도 값으로 받을 수 있음.

    //http:localhost:8080/api/v1/delete-api/request1?email=value
    @DeleteMapping(value = "/request1")
    public String getRequest1(@RequestParam String email)
    {
        return "e-mail : "+ email;
    }
}
