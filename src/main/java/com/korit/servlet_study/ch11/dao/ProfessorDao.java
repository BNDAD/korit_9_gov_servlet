package com.korit.servlet_study.ch11.dao;


import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProfessorDao {
    private final DBConnectionMgr mgr;

    public List<Professor> findAll() {
        List<Professor> professors = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
                con = mgr.getConnection();
                String sql = """
                select 
                        professor_id,
                        professor_name
                from
                        professor_tb
                order by
                        professor_id
                """;

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Professor professor = Professor.builder()
                            .professorId(rs.getInt("professor_id"))
                            .professorName(rs.getString("professor_name"))
                            .build();
                    professors.add(professor);
                }
        } catch (Exception e) {
                e.printStackTrace();
        }finally {
                mgr.freeConnection(con, ps, rs);
        }
        return professors;
    }




    public void insert(Professor professor) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            con = mgr.getConnection();

            String sql = """
                insert into professor_tb
                values (default, ?)
            """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, professor.getProfessorName());


            if (ps.executeUpdate() < 1) {
                throw new SQLException();
            }

            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                int professorId = rs.getInt(1);
                professor.setProfessorId(professorId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            mgr.freeConnection(con, ps, rs);
        }
    }

}
