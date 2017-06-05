package kz.keste.controllers.actions;

import kz.keste.database.Database;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteClassAction implements Action {

    private final String DELETE_CLASS_QUERY = "DELETE FROM classes WHERE class_id = ?";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(DELETE_CLASS_QUERY);
        stmt.setInt(1, Integer.parseInt(request.getParameter("class_id")));
        stmt.execute();

        connection.close();
        stmt.close();
        return null;
    }
}
