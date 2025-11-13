package com.korit.servlet_study.ch11.dto;

import com.korit.servlet_study.ch11.entity.Course;
import com.korit.servlet_study.ch11.entity.Student;
import lombok.Data;

@Data
public class CourseDto {
    private String courseCode;
    private String courseName;
    private int professorId;
    private int credit;
    private int enrollmentCapacity;
    private String classroom;


    public Course toEntity() {
        return Course.builder()
                .courseCode(courseCode)
                .courseName(courseName)
                .professorId(professorId)
                .credit(credit)
                .enrollmentCapacity(enrollmentCapacity)
                .classroom(classroom)
                .build();

    }
}
