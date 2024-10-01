package com.college.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@NoArgsConstructor
@Setter
@Table(name="student")
@Entity
@NamedEntityGraph(name="studentEntityGraphs",attributeNodes = {@NamedAttributeNode(value="academics"), @NamedAttributeNode(value="branch")})
public class    StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",unique = true)
    private Integer id;
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
    @Column(name="address",nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name="branch_id")
    private BranchEntity branch;
    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<AcademicEntity> academics;
}
