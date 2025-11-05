package com.korit.servlet_study.ch08;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/ch08/board")
public class BoardServlet extends HttpServlet {
    List<Board> boards = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), boards);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        BufferedReader br = req.getReader();
//        StringBuilder json = new StringBuilder();


//        while (true) {
//            String line = br.readLine();
//            if (Objects.isNull(line)) {
//                break;
//            }
//            json.append(line);
//        }
//        System.out.println(json);

        ObjectMapper mapper = new ObjectMapper();

        Board board = mapper.readValue(req.getInputStream(), Board.class);


        boards.add(board);
        System.out.println(board);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        Response response = new Response();

        response.setMessage("게시물 작성 완료");
        System.out.println(response);

        mapper.writeValue(resp.getWriter(), response.getMessage());




    }

}
