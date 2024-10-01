package com.college.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

public class StudentDto {
    private  String firstName;
    private String familyName;
    private String gender;
    private String mobileNumber;
    private String email;
    private Date dob;
    private String address;
    private int branchId;
//    private int academicId;
}
