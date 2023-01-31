package com.springboot.api.controller;
// PUT API
/*
    PUT API 는 웹 애플리케이션 서버를 통해 데베 같은 저장소에 리소스 값을 업데이트
        할때 사용

        vs POST API -> 요청을 받는다 vs 요청을 받아 실제 데베에 반영한다.

        하지만 둘다 Body 에서 받기때문에 별차이는 없다.
 */


import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {


    //http://localhost:8080/api/v1/put-api/member
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> putData)
    {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach((map ->
        {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        }));

        return sb.toString();
    }
    // 어떤 값이 들어올지 모를때..?


    //대부분 지정된 형식대로 넣기 때문에 DTO 를 활용하면.
    //http:localhost:8080/api/v1/put-api/member1
    @PutMapping(value = "/member1")
    public String putMemberDto(@RequestBody MemberDto memberDto)
    {
        return memberDto.toString();
    }

    @PutMapping(value = "/member2")
    public MemberDto postMemberDto2(@RequestBody MemberDto memberDto)
    {
        return memberDto;
    }
    /*
        member1 vs member2
            -> 이 둘의 차이 - > 바디에 같은 값을 넣고 반환 받았을때
            member1 -> text /plain 으로 값을 받고 (일반 문자열)
            member2 -> json 으로 받음



            => @RespondBody 어노테이션은 자동으로 값을 JSON 으로 변환해서 전달하는 역할 수행.
            ?? 이게 뭐임..? -> @RequestController 어노테이션으로 지정된 클래스는 생략가능

     */

    //ResponseEntity 를 활용한 PUT 메서드 구현.
    /*
        스프링 프레임 워크에는 HttpEntity 클래스 존재
            => Header, Body 로 구성된 요청과 응답을 구성하는 역할을 수행.
     */

    //http://localhost:8080/api/v1/put-api/member3
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto)
    {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED) // 응답코드 202
                .body(memberDto);
    }

}
