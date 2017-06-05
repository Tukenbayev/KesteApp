package kz.keste.controllers.actions;

import com.google.gson.Gson;
import kz.keste.database.Database;
import kz.keste.security.PasswordAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class LoginAction implements Action{

    private final String PASSWORD_QUERY = "SELECT id, email, password FROM schools WHERE email = ?";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int school_id = 0;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String storedPassword = "";
        HttpSession session = request.getSession();

        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(PASSWORD_QUERY);
        statement.setString(1,email);
        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            school_id = rs.getInt("id");
            storedPassword = rs.getString("password");
        }

        connection.close();
        statement.close();
        rs.close();

        Map<String, Object> map = new HashMap<>();

        if (PasswordAuthentication.check(password, storedPassword)) {
            session.setAttribute("username", email);
            session.setAttribute("school_id",school_id);
            map.put("isCorrect", true);
            write(response, map);
        } else {
            map.put("isCorrect", false);
            write(response, map);
        }

        return null;

    }

    private void write(HttpServletResponse response, Map<String,Object> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(map));
        writer.close();
    }
}
