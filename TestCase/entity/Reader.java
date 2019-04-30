package com.gupaoedu.vip.spring.TestCase.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/*
create by Jack on 2019/4/29
 */
@Data
@Table(name="readers")
public class Reader {
    @Column(name="re_id") private int id;
    private String phone;
    @Column(name="re_name") private String name;
}