package com.springboot.api.controller;
//POST API 만들기
/*
    POST API 는 웹 애플리케이션을 통해 -> 데베 들의 저장소에
        리소스를 저장할때 사용하는 API

    => GET API 에서는 URL 의 경로나 파라미터에 변수를 넣어 요청했지만
            POST API 는 HTTP BODY 에 담아 저장한다.

 */


import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/post-api") // 공통된 url 을 설정 -> 쓸때마다 이거 다쓰기 귀찮자낭..
public class PostController {

    //RequestMapping 으로 구현하기
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample(){
        return "Hello Post API";
    }
    // POST 요청을 받는 메소드
    /*
        POST 형식의 요청은 클라이언트 -> 서버 로의 리소스를 저장하는데 사용
            => POST 요청에서는 리소스를 담기위해 HTTP Body 에 JSON 형식으로  값을 주고받음.


          ++ JSON :
                JAVA Script Object Notation 의 줄임말 -> 자바스크립트의 객체문법을 따르는 문자기반의 데이터 포멧
                대채로 네트워크를 통해 데이터를 전달할때 문자열 형태로 작성되기 때문에 파싱하기도 쉽고 해성 자주 이용...
     */

    //http://localhost:8080/api/v1/post-api/member
    @PostMapping(value = "/member")  // method 요소를 정의하지 않아도 됩니당
    public String postMember(@RequestBody Map<String, Object> postData)
    //@RequestBody : HTTP 의 Body 내용을 해당 어노테이션이 지정된 객체에 매핑하는 역할
    {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
        });
        return sb.toString();
    }

    //http://localhost:8080/api/v1/post-api/member2
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto)
    {
        return memberDto.toString();
    }

    //뭐 요론식의 으용도 가능 하다네용...

}
