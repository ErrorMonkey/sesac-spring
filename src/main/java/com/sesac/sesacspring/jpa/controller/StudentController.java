package com.sesac.sesacspring.jpa.controller;

import com.sesac.sesacspring.jpa.dto.StudentDto;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
//    @GetMapping("/count")
//    public int getCountAll(){}

    @GetMapping("/all")
    public List<StudentDto> getAll(){
        // student 의 목록을 전부 가져와 보여주는 api
        List<StudentDto> result = studentService.getStudentAll();
        return result;
    }

//    @GetMapping("/search")
//    public ? getSearch(@RequestBody int id){}

    // 1. 전체 검색 ( select * from student )
    // 2. 삽입 ( insert into ~~~ )
    @GetMapping("/insert") // /student/insert?name=이름
    public String insertStudent(@RequestParam String name,
                                @RequestParam String nickname,
                                @RequestParam Student.LoginType type) {
        // 이름, 닉네임, login_type
        return studentService.insertStudent(name, nickname, type);
    }
    // 3. 조건에 따른 검색 ( select * from student where name='' )
    @GetMapping("/search/name") // /search/name?name=이름
    public String searchStudentByName(@RequestParam String name) {
        return studentService.searchStudentByName(name);
    }
    // 4. 조건에 따른 검색 (2) ( select * from student where id='' )
    @GetMapping("/search/id")
    public String searchStudentById(@RequestParam int id) {
        return studentService.searchStudentById(id);
    }

    @GetMapping("/count")
    public String countByNickname(@RequestParam String nickname) {
        return studentService.countByNickname(nickname);
    }

    @GetMapping("/update")
    public String updateUserName(@RequestParam int id,
                                 @RequestParam String name) {
        return studentService.updateUserName(id, name);
    }

}
