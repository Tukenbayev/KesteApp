package kz.keste.controllers.actions;

import com.google.gson.Gson;
import com.mysql.jdbc.Statement;
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

public class CheckSubjectAction implements Action {

    private final String CHECK_SUBJECT_QUERY = "SELECT * FROM subjects WHERE LOWER(subject_name) = ?";
    private final String INSERT_SUBJECT_QUERY = "INSERT INTO subjects VALUES(NULL,?)";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subjectName = request.getParameter("subjectName");
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(CHECK_SUBJECT_QUERY);
        stmt.setString(1,subjectName.toLowerCase());
        ResultSet resultSet = stmt.executeQuery();

        int subjectId = 0;

        if (resultSet.next()){
            subjectId = resultSet.getInt("subject_id");
        }else{
            PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,subjectName);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next())
                subjectId = rs.getInt(1);

            statement.close();
        }

        Map<String,Object> data = new HashMap<>();
        data.put("subjectId", subjectId);
        data.put("subjectName", subjectName);
        write(data,response);

        connection.close();
        stmt.close();
        return null;
    }

    private void write(Map<String, Object> data, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(data));
        writer.close();
    }
}
