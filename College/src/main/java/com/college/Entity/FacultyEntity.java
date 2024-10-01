package com.college.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="faculty")
public class FacultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer facultyId;

    @OneToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity employeeId;

    @JsonIgnore
    
    @ManyToMany
    @JoinTable(name="branch_faculty",joinColumns = @JoinColumn(name="faculty_id"),
            inverseJoinColumns =@JoinColumn(name="branch_id") )
    private Set<BranchEntity> branches;
}
