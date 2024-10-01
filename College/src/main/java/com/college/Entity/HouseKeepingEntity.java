package com.college.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="housekeeping")
public class HouseKeepingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Integer id;
    @OneToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity employeeId;

}
