package com.sesac.sesacspring.mybatis.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardDto {
    private int boardID;
    private int id;
    private String title, content, writer, registered;
    private Date date;
    private int no;
}
