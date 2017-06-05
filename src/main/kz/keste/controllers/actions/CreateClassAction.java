package kz.keste.controllers.actions;

import com.google.gson.Gson;
import kz.keste.database.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class CreateClassAction implements Action {
    private final String CREATE_CLASS_QUERY = "INSERT INTO classes VALUES(NULL, ?,?,?)";
    private final String CHECK_CLASS_EXISTANCE_QUERY = "SELECT class_id FROM classes WHERE class_number = ? AND " +
            "class_letter = ? AND school_id = ?";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection connection = Database.getConnection();
        PreparedStatement createStmt = connection.prepareStatement(CREATE_CLASS_QUERY);
        PreparedStatement checkStmt = connection.prepareStatement(CHECK_CLASS_EXISTANCE_QUERY);

        int classNumber = Integer.parseInt(request.getParameter("class_Number"));
        String classLetter = request.getParameter("class_Letter");
        int schoolId = Integer.parseInt(request.getParameter("school_id"));

        checkStmt.setInt(1,classNumber);
        checkStmt.setString(2,classLetter);
        checkStmt.setInt(3,schoolId);

        ResultSet rs = checkStmt.executeQuery();
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Map<String,Object> map = new HashMap<>();

        if (rs.next()){

            map.put("isExist",true);
            writer.write(new Gson().toJson(map));
        }else{
            createStmt.setInt(1,classNumber);
            createStmt.setString(2,classLetter);
            createStmt.setInt(3,schoolId);

            createStmt.execute();

            map.put("isExist", false);
            writer.write(new Gson().toJson(map));
        }

        writer.close();
        rs.close();
        checkStmt.close();
        connection.close();
        createStmt.close();

        return null;
    }
}
