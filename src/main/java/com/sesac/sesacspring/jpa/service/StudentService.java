package com.sesac.sesacspring.jpa.service;

import com.sesac.sesacspring.jpa.dto.StudentDto;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDto> getStudentAll() {
        // Repository 에서 받은 Entity 형태를 반환함
        List<Student> result = studentRepository.findAll();
        List<StudentDto> students = new ArrayList<>();

        for (Student student : result) {
            // Builder 패턴: 객체를 만들 때 순서에 의해 생기는 문제,
            //              명시적이지 못한 문제를 해결하기 위해 나온 방법
            // === 기존 객체 만들던 방식 ===
            // 생성자 주입: 여러개의 필드가 있을 때 순서를 지켜줘야 함
            // setter: 필드 개수만큼 매번 메소드를 만들어야 함
            StudentDto studentDto = StudentDto.builder() // new Builder
                    .name(student.getName())
                    .nickname(student.getNickname())
                    .build();
//            위 코드는 아래 코드와 동일
//            StudentDto studentDto = new StudentDto();
//            studentDto.setName("이름")...
            students.add(studentDto);
        }
        return students;
    }
}
