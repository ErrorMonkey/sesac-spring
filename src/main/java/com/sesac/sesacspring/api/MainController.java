package com.sesac.sesacspring.api.controller;

import com.sesac.sesacspring.api.Dto.UserDTO;
import com.sesac.sesacspring.api.vo.UserDataVo;
import com.sesac.sesacspring.api.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// @RestController // @Controller + @ResponseBody
public class MainController {
    @GetMapping("/")
    public String getMain() { return "request"; }

    // ===GET===
    // 매개변수를 넘겨받는 방법
    // 1. /test?eid=123 -> @RequestParam
    // 2. /test/123 -> @PathVariable

    @GetMapping("/get/response1")
    // ?key=value
    // ?name=
    // @RequestParam 은 기본값으로 required=true
    public String getResponse1(@RequestParam (value = "name") String i, Model model) {
        // @RequestParam 어노테이션
        // - ?name=112&id=11&age=abc ( o )
        // - ?id=11&hashtag=abc ( x )
        // - string query( ?뒤의 값 ) 에서 key("name") 에 대한
        //   value("112")를 변수("i")에 매핑
        // - required=true 기본값 -> 요청 url 에서 설정한 key 가 필수로 있어야 해요.
        model.addAttribute("name", i);
        return "response";
    }
    @GetMapping("/get/response2")
    // required=false 옵션 ( @RequestParam(value="", required=false) )
    // - query string 에서 특정 key 를 옵셔널하게 받아야 하는 경우
    // ex) 검색할 때 (검색어(필수 해시태그(선택)))
    // @RequestParam(value="search") String search,
    // @RequestParam(value="hashtag", required=false) String hashtag,
    // ?search=검색어
    // ?search=검색어&hashtag=코딩
    public String getResponse2(
            @RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "response";
    }

    @GetMapping("/get/response3/{param1}/{param2}")
    // url 안에 넣을 때 @PathVariable
    public String getResponse3(@PathVariable String param1, @PathVariable (value = "param2") String age, Model model) {
        /*
        @RequestVariable 어노테이션
        - /test/{id} 형식의 url 경로로 데이터를 넘겨줄 때 받는 방법
        - 기본적으로 경로 변수의 값을 필수로 받아야 하기 때문 ( 보내지 않으면 404 error )
         */
        model.addAttribute("name", param1);
        model.addAttribute("age", age);

        return "response";
    }

    // @PathVariable 을 보낼 때 선택적으로 처리해야 한다면
    @GetMapping({"/get/response4/{param1}","/get/response4/{param1}/{param2}"})
    public String getResponse4(
            @PathVariable String param1,
            @PathVariable(required = false, value = "param2") String age,
            Model model) {
        // 중요! optional 한 parameter 는 맨 뒤에 오도록 설정
        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "response";
    }

    // post 방식 - @RequestParam
    @PostMapping("/post/response1")
    public String postResponse1(
            @RequestParam(value = "name") String a,
            @RequestParam(value = "age") String b,
            Model model){
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    }
    @PostMapping("/post/response2")
    public String postResponse2(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value = "age", required = false) String b,
            Model model){
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    }
    // @ReponseBody
    // 응답 시 객체를 json 형태로 리턴한다. ( 직렬화 )
    // express res.send 유사
    @PostMapping("/post/response3")
    @ResponseBody
    public String postResponse3(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value = "age", required = false) String b,
            Model model){
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return a + " hello " + b ;
    }

    @GetMapping("dto/response1")
    @ResponseBody
    public String dtoResponse1(@ModelAttribute UserDTO userDTO) {
        // DTO: getter 와 setter 가 있는 객체
        // Get 방식에서 DTO 객체로 담아서 값이 받아지는구나.
        // @ModelAttribute: HTML 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑
        // 매핑 = setter 함수 실행
        // ?name=홍길동&age=10 -> setName("홍길동") setAge("10")
        return userDTO.getName() + " " + userDTO.getAge();

    }
    // @RequestBody: 요청의 본문에 있는 데이터(body)를 받는 친구
    @GetMapping("/dto/response11")
    @ResponseBody
    public String dtoResponse11(@RequestBody UserDTO userDTO) {
        return userDTO.getName() + " " + userDTO.getAge();
    }

    // 일반 폼 전송 -> www-x-form-urlencoded => 쿼리 매개변수
    // 일반 폼 전송 -> RequestBody 값을 x
    // RequestBody 는 요청의 본문에 있는 데이터(body)를 처리할 수 있기 때문에
    // json, xml 일 때만 실행이 가능
    // application/json

    // 일반 폼 전송 - DTO ( getter, setter 모두 있는 친구 )
    // 1) 어노테이션 없이 DTO 로 받을 경우 -> o
    // 2) @ModelAttribute DTO 로 받을 경우 -> o
    // 3) @RequestBody DTO 로 받을 경우 -> 오류

    // 일반 폼 전송은 www-x-form-urlencoded 형식이기 때문에
    // get 이든 post 든 요청의 본문에 데이터가 들어가는 게 아닌 폼 데이터 형태로
    // url 로 데이터가 전송됨. -> 즉, 일반 폼 전송은 @RequestBody 사용 불가

    // 일반 폼 전송 - VO
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVO userVO) {
        return userVO.getName() + ' ' + userVO.getAge();
    } // => null
    @GetMapping("/vo/response2s")
    @ResponseBody
    public String voResponse2(UserVO userVO) {
        return userVO.getName() + ' ' + userVO.getAge();
    } // => null
    @GetMapping("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVO userVO) {
        return userVO.getName() + ' ' + userVO.getAge();
    } // => 오류

    // - axios 를 이용한 데이터 처리
    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosResponse1(@RequestParam String name, @RequestParam String age) {
        return name + " " + age;
    } // 1. Axios - get - @RequestParam -> o
    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosResponse2(UserDTO userDTO) {
        // @ModelAttribute
        // axios = application/json
        return userDTO.getName() + " " + userDTO.getAge();
    } // 2. Axios - get - @ModelAttribute -> o

    @PostMapping("/axios/response3")
    @ResponseBody
    // url 이었는데, axios post 는 url 에 데이터가 x
    // url 에 아무것도 없는데 name, age required=true 이기 때문에 에러 발생
    public String axiosRes3(@RequestParam String name, @RequestParam String age){
        return "이름: " + name + ", 나이: "+ age;
    }

    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosRes4(UserDTO userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    } // axios + post 데이터 -> @ModelAttribute o (null)
    // @ModelAttribute 를 이용해 데이터를 보냈을 때 값이 null
    // axios 로 보내면 url 로 데이터를 보내는 게 아니라 본문으로 데이터를 보내게 된다.
    // 즉, @ModelAttribute 가 값을 볼 수 없다

    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosRes5(@RequestBody UserDTO userDTO){
        // axios post 로 데이터를 보내면 요청의 본문(body)에 데이터가 들어간다.
        // @RequestBody 는 요청의 본문에 있는 데이터를 읽을 수 있다.
        // UserVO 클래스는 setter 메소드가 없음.
        // @RequestBody 는 데이터를 각각의 필드(변수)에 직접 값 주입
        // @RequestBody 는 UserVO UserDTO 상관 없이 setter 메소드의 유무와 관계 없이 변수에 값을 넣을 수 있다.
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    } // axios + post 데이터 -> @RequestBody o

//1. 일반 폼 전송
// - RequestParam : GET, POST
// - PathVariable : GET
//2. DTO 이용 - 일반 폼 전송(url)
//- GET? O
//- POST + ModelAttribute? O
//- POST + RequestBody? X
//3. VO 이용 - 일반 폼 전송
//- GET? NULL
//- POST + ModelAttribute? Null
//- POST + RequestBody? X
//4. AXIOS - DTO
//- GET RequestParam : o
//- GET ModelAttribute : o
//- GET RequestBody : x
//- POST RequestParam : x
//- POST ModelAttribute : null
//- POST RequestBody : o
//5. AXIOS VO
//- GET RequestParam : o
//- GET ModelAttribute : NULL
//- GET RequestBody : x
//- POST RequestParam : x
//- POST ModelAttribute : null
//- POST RequestBody : o

    @GetMapping("/prac02")
    public String getPrac02() {
        return "prac02";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String axiosSignup(@RequestBody UserDataVo userDataVo) {
        return userDataVo.getName()+" 님 회원가입 성공! 생년월일: " + userDataVo.getBirth() + ", 성별: " + userDataVo.getGender();
    }
}
