package kz.keste;

import com.google.gson.Gson;
import kz.keste.dao.SchoolDAO;
import kz.keste.dao.SchoolDAOImpl;
import kz.keste.database.Database;
import kz.keste.models.School;
import kz.keste.service.SchoolService;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        String CHECK_SUBJECT_QUERY = "SELECT * FROM subjects WHERE LOWER(subject_name) = ?";
        String INSERT_SUBJECT_QUERY = "INSERT INTO subjects VALUES(NULL,?)";

        String subjectName = "Mathematic";
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(CHECK_SUBJECT_QUERY);
        stmt.setString(1,subjectName);
        ResultSet resultSet = stmt.executeQuery();

        if (!resultSet.next()){
            PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT_QUERY,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,subjectName);
            statement.execute();

            ResultSet resultSet1 = statement.getGeneratedKeys();
            if (resultSet1.next())
                System.out.println(resultSet1.getInt(1));
        }
    }
}
