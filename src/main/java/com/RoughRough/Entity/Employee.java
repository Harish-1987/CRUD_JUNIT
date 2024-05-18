package com.RoughRough.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="dept", nullable =false)
    private String dept;

    @Column(name="city", nullable = false)
    private String city;
}
