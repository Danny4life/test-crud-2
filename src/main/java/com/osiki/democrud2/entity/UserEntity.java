package com.osiki.democrud2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user_tbl")
public class UserEntity extends BaseClass{

    private String firstname;
    private String lastname;
    private String email;
}
