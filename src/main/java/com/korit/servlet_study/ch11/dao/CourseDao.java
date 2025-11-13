package com.korit.servlet_study.ch11.dao;


import com.korit.servlet_study.ch11.entity.Course;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@RequiredArgsConstructor
public class CourseDao {
    private final DBConnectionMgr mgr;

    public  void insert(Course course){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            con = mgr.getConnection();
            String sql = """
                    insert into course_tb
                    values (default, ?, ?, ?, ?, ?, ?)
            """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, course.getCourseCode());





        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
