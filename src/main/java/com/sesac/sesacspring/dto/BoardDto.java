package com.sesac.sesacspring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private Date date;
}
