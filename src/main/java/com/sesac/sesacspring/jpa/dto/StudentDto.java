package com.sesac.sesacspring.jpa.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentDto {
    private String name;
    private String nickname;
}
