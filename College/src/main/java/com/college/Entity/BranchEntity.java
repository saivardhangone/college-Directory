package com.college.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="branches")
public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id",nullable = false)
    private Integer id;

    @Column(name="BRANCH_NAME",nullable = false,unique = true)
    private String branchName;
    @JsonIgnore
    @OneToMany(mappedBy = "branch")
    private Set<StudentEntity> students;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name="branch_faculty",joinColumns = @JoinColumn(name="branch_id"),
            inverseJoinColumns =@JoinColumn(name="faculty_id") )
    private Set<FacultyEntity> faculties;


    public BranchEntity(int i, String name) {
        this.id=i;
        this.branchName=name;
    }
}
