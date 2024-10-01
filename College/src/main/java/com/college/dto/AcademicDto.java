package com.college.dto;

import com.college.Entity.StudentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AcademicDto {
    private int academicYear;
    private int semester;
    private int studentId;
}
