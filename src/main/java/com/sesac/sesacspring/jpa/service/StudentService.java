package com.sesac.sesacspring.jpa.service;

import com.sesac.sesacspring.jpa.dto.StudentDto;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public String insertStudent(String name, String nickname, Student.LoginType type) {
        Student student = Student.builder().name(name).nickname(nickname).type(type).build();
        // save 메소드는 데이터를 저장하고 저장한 데이터를 반환한다
        Student newStudent = studentRepository.save(student);
        // newStudent: save 한 후 반환되는 Entity 객체
        return newStudent.getId() + newStudent.getName();
    }

    public String searchStudentByName(String name) {
        List<Student> students = studentRepository.findByName(name);
//        return "id는 " + student.getId() + "입니다.";
        return "해당하는 이름의 사용자는 " + students.size() + "명입니다.";
    }

    public String searchStudentById(int id) {
//        // Optional<T>: Java 8 부터 등장한 친구
//        // null 일 수도 있는 객체를 감싸는 wrapper 클래스
//        Optional<Student> student = studentRepository.findById(id);
//        if (student.isPresent()) {
//            // isPresent: 객체의 여부를 boolean 으로 반환
//            return student.get().getName();
//            // get: Optional 에 담긴 객체를 반환
//        }
//        return "null";

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자가 없음"));
        return student.getName();
        // orElse(): 있으면 반환하고 없으면 다른 값 반환
        // orElseThrow(): 있으면 반환하고 없으면 error 처리
    }

    public String countByNickname(String nickname) {
        return "찾는 유저 수는 " + studentRepository.countByNickname(nickname) + "명 입니다.";
    }

    public String updateUserName(int id, String name) {
//        Student modifiedStudent = studentRepository
//                .save(Student.builder().id(id).name(name).build());
        // save(T): 새로운 entity 를 insert or 기존 entity 를 update
        // T의 기본값(pk)의 상태에 따라 다르게 동작
        // - pk 값이 존재하는 경우: pk와 연결된 entity 를 update
        // - pk 값이 없는 경우: 새로운 entity 를 insert
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("id is wrong"));
        Student modifiedStudent =
                Student.builder()
                        .id(id).name(name).nickname(student.getNickname())
                        .type(student.getType()).build();
        studentRepository.save(modifiedStudent);

        return modifiedStudent.getId()+"번 유저 이름을 "+modifiedStudent.getName() + "(으)로 수정 완료";
    }

}
