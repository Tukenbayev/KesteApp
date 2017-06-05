package kz.keste.controllers.actions;

import com.google.gson.Gson;
import kz.keste.database.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class CheckTeachersPhoneAction implements Action {

    private final String GET_TEACHERS_PHONE_QUERY = "SELECT phone FROM teachers WHERE phone = ?";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long phone = Long.parseLong(request.getParameter("phone"));
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_TEACHERS_PHONE_QUERY);
        statement.setLong(1,phone);

        ResultSet resultSet = statement.executeQuery();
        Map<String,Object> answer = new HashMap<>();

        if (resultSet.next())
            answer.put("isExist",true);
        else
            answer.put("isExist", false);

        write(answer, response);

        return null;
    }

    private void write(Map<String, Object> answer, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(new Gson().toJson(answer));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
