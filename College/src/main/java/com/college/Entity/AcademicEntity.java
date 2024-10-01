package com.college.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="academic")
public class AcademicEntity  {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    @Column(name="id",nullable = false)
    private Integer id;
    @Column(name="academic_year",nullable = false)
    private Integer academicYear;
    @Column(name="semester",nullable = false)
    private Integer semester;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

//    @JsonIgnoreProperties(value = {"academics", "hibernateLazyInitializer"})
    private StudentEntity student;
}
