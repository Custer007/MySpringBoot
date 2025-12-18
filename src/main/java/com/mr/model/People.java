package com.mr.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class People {

    private String name;

    private String gender;

    private Integer age;

}
