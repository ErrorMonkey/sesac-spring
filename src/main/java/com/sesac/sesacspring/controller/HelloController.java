package com.sesac.sesacspring.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Iterator;

@Controller
// @Controller: 해당 클래스가 Controller 의 역할을 하는 클래스라는 것을
// Spring Container 에게 알려준다.
public class HelloController {
    // GetMapping: URL 을 매핑 시켜주는 친구
    // 클라이언트가 "/hi" 라는 경로로 GET method 로 접근하면 아래 메소드를 실행시켜라
    @GetMapping("/hi")
    public String getHi(Model model) {
        // Model: Controller 안의 메서드가 파라미터로 받을 수 있는 객체 중 하나
        // Model 안에 정보를 담아서 view 로 전달
        // IoC: 개발자가 직접 model 을 생성 x
        model.addAttribute("name", "홍길동");
        // node 에서 res.render("hi", {name:'홍길동'}) 이 코드와 비슷함
        model.addAttribute("name2", "<strong>새싹</strong>");
        model.addAttribute("age", 10);

        char[] alphabetArr = new char[26];
        char alphabet = 'A';
        for (int i = 0; i < 26; i++) {
            alphabetArr[i] = alphabet;
            alphabet++;
        }
        model.addAttribute("item1", alphabetArr);

        return "hi"; // 템플릿 파일의 이름
        // node 에서 res.render("hi", {name:'홍길동'}) 이 코드와 비슷함
    }
    @GetMapping("/people")
    public String getPeople(Model model) {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("kim", 10));
        people.add(new Person("lee", 20));
        people.add(new Person("hong", 30));
        people.add(new Person("park", 40));
        people.add(new Person("shin", 50));
        model.addAttribute("people", people);

        Person p = new Person("h", 10);
//        System.out.println(p.getName());
        // Lombok 의 어노테이션은 자바가 실행하면서 작동해서 그냥 쓰면 오류가 뜰 수 있다

        return "people";
    }

    @Getter
    @Setter
    public class Person {
        // @Getter
        // @Setter
        private String name;
        private int age;
//        public String getName() {
//            return name;
//        }
//        public int getAge() {
//            return age;
//        }
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
