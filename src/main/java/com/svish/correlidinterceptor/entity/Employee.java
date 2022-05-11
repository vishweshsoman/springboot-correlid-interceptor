package com.svish.correlidinterceptor.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    String name;

    @Column
    String email;

    @Column
    Integer age;

}
