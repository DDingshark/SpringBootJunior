package com.springboot.api.controller;
import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.Map;

@RestController
// 소캣의 진입정 -> RestController 을 보면 -> 이걸 힌트로 해당 클래스 DemoController new 되어 heap 에 쓴다.

// 메모리에 띄우면서 클래스 구현부 내부의 것들을 소캣의 진입정으로 올림.

@RequestMapping("/api/v1/get-api")
// 하지만 스프링 4.3 이후 버전 부터는
// GetMapping, PostMapping, PutMapping, DeleteMapping 을 사용하기에 처음 이니,, 뭐 이런게 있었당?! 하는 느낌으로 소개..

public class GetController {

    //http:localhost:8080/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello()
    {
        return "Hello World";
    }


    //http:localhost:8080/api/v1/get-api/name
    // 매개변수가 없는 GET 메서드
    @GetMapping(value = "/name")
    // endpoint 를 파싱해서 해당 메소드를 찾음,
    public String getName()
    {
        return "DDIng Joo";
    }
    // 실제 업무에서는 매개변수를 받지않는 메서드는 거의 사용하지 X
    //   -> 웹 통신의 목적 = 데이터를 주고받는것,,!
    // =>> 매개변수를 받는 방법에는 뭐가있슴까..?
    //      URL 자체에 변수를 받는 법이 있음.

    //http:localhost:8080/api/v1/get-api/variable1/{String 값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable)
    {
        return variable;
    }
    // {} 위치의 String 값을 받아오는 Get 메소드 입니당...!
    //      주의 -> 위치를 정확히 {}요놈으로 표기 해줘야하공 GetMapping 어노테이션과
    //                                        PathVariable 에 지정된 변수 이름을 동일하게 맞쳐야댐.

    //                  -> 안해도 되는댕... 안할거면... 이렇게....

    //http:localhost:8080/api/v1/get-api/variable2/{String 값}
    @GetMapping(value = "variable2/{variable}")
    public String getVariable2(@PathVariable("variable")String str)
    {
        return str;
    }

    //요로코롬 걍 Data를 받아오는 okok 굳굳 ... 근디유..
    //  DB에 박을 쿼리들을 받을땐...?
    //   -> RequestParam 을 이용해서 URL ? 를 기준으로  {키} = {값} 형태로 구성된 요청을 전송

    //http:localhost:8080/api/v1/get-api/request1?name=value1@email=value2&organization =value3
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization )
    {
        return name + " "+ email+" "+organization;
    }

    //쿼리 스트링에 어떤 값이 들어올지 모를때??
    //http:localhost:8080/api/v1/get-api/request2?key1=value&kwy2=value2

    @GetMapping(value = "/request2")
    public String getRequestParam2(
            @RequestParam Map<String, String> param)
    {
        StringBuilder sb =new StringBuilder();
        param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));
        return sb.toString();
    }
    // 뭐 ... 값이 없을때 사용하거나.... 매개변수 항목이 일정하지 않거나....

    //tip -> URL vs URI
    // URL -> 웹주소 -> 리소스가 어디있는지 알려주는 경로
    // URI -> 특정 리소스를 식별하기위한 '식별자' -> 결국 URL 을통해 어디서버에 있는지 접근하고 리소스에 접근하기위해 URI가 필요


    //http:localhost:8080/api/v1/get-api/request3?name=value1@email=value2&organization=value3
    @GetMapping(value = "/request3")
    public String getRequest3(MemberDto memberDto)
    {
        //== return memberDto.getName()+ " "+ memberDto.getEmail()+ " "+memberDto.getOrganization();
        return memberDto.toString(); // => 오버라이딩 했자낭...
    }

    //Swagger2 를 이용한 명세를 추가.
    @ApiOperation(value = "GET 메서드예제",notes = "@RequestParam 을활용한 GET Method")
    @GetMapping(value = "/request4")
    public String getRequestParam4(
            @ApiParam(value = "이름", required = true)@RequestParam String name,
            @ApiParam(value = "이메일", required = true)@RequestParam String email,
            @ApiParam(value = "학교", required = true)@RequestParam String organization
    ){
        return name + " " + email+" "+organization;
    }

    // Logger 선언
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);
    //http:localhost:8080/api/v1/get-api/logger-get-hello
    @GetMapping(value = "/logger-get-hello")
    public String gethello(){
        LOGGER.info("getHello 메서드가 호출되었습니다,");
        return "Hello";
    }
    //http:localhost:8080/api/v1/get-api/logger-get-value/{String 값}
    @GetMapping(value = "/logger-get-value/{variable}")
    public String getvelue(@PathVariable String variable){
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

}






