package com.mr.model;


import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Table(name = "ur_user")
@Data
public class UrUser {

    @Id
    private Integer id;

    private String name;

    private String phone;

    private String email;

    public UrUser(Integer id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
