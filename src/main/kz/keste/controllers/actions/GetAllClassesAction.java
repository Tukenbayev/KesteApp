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

public class GetAllClassesAction implements Action {
    private final String GET_CLASSES_QUERY = "SELECT * FROM classes WHERE school_id = ? ORDER BY class_letter";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int school_id = Integer.parseInt(request.getParameter("school_id"));
        Map<Integer,Object> allClasses = new HashMap<>();
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_CLASSES_QUERY);
        statement.setInt(1,school_id);
        ResultSet schoolClasses = statement.executeQuery();

        while (schoolClasses.next()){
            int class_id = schoolClasses.getInt("class_id");
            Map<String,Object> classInfo = new HashMap<>();
            classInfo.put("class_Number", schoolClasses.getInt("class_number"));
            classInfo.put("class_Letter", schoolClasses.getString("class_letter"));
            allClasses.put(class_id,classInfo);
        }

        write(response,allClasses);

        return null;
    }

    private void write(HttpServletResponse response, Map<Integer, Object> allClasses) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(allClasses));
        System.out.println(new Gson().toJson(allClasses));
        writer.close();
    }


}
