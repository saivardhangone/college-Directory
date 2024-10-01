package com.college.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",nullable = false)
    private Integer id;
    @Column(name="section",nullable = false)
    private String section;
    @OneToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity employeeId;


}
