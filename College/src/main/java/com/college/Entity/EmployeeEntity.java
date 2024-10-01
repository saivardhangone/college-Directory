package com.college.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",unique = true)
    private Integer employeeId;
    @Column(name="first_name",nullable = false)
    private String firstName;
    @Column(name="family_name",nullable = false)
    private String familyName;
    @Column(name="gender",nullable = false)
    private String gender;
    @Column(name="mobile_number",nullable = false,unique = true)
    private String mobileNumber;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="dob",nullable = false)
    private Date dob;
    @Column(name="ADDRESS",nullable = false)
    private String address;

    @JsonIgnore
    @OneToOne(mappedBy = "employeeId")
    private AdminEntity admin;
    @JsonIgnore
    @OneToOne(mappedBy = "employeeId")
    private FacultyEntity faculty;

    @JsonIgnore
    @OneToOne(mappedBy = "employeeId")
    private HouseKeepingEntity houseKeeping;


}
