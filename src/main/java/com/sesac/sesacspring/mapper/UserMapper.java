package com.sesac.sesacspring.mapper;

import com.sesac.sesacspring.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    // sql 문을 정의 or xml 파일을 읽거나

    // xml 파일을 읽어서 실행하겠다.
    List<User> retreiveAll();

    // sql 문 정의
    @Insert("insert into user(name, nickname) values(#{name}, #{nickname})")
    void createUser(String name, String nickname);
    void createUser2(User user); // 객체로 묶어서 보낼 수도 있음

    @Update("update user SET nickname=#{nickname} where id = #{id}")
    void updateUser(int id, String nickname);

    @Delete("delete from user where id = #{id}")
    void deleteUser(int id);
}
