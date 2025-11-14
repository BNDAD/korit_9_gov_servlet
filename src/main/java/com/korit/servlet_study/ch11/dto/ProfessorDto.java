package com.korit.servlet_study.ch11.dto;


import com.korit.servlet_study.ch11.entity.Professor;
import lombok.Data;

@Data
public class ProfessorDto {
    private int professorId;
    private String professorName;


    public Professor toEntity() {
        return Professor.builder()
                .professorId(professorId)
                .professorName(professorName)
                .build();
    }
}
