package com.mr.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {

    private Integer id;

    private String name;

    private String studentNo;

    private String gender;

    private String className;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
