package com.gupaoedu.vip.spring.TestCase.entity;

import lombok.Data;

import javax.persistence.Table;

/*
create by Jack on 2019/4/29
 */
@Data
@Table(name="authors")
public class Author {
    private int au_id;
    private String email;
    private String au_name;
}