package kz.keste.dao;

import kz.keste.database.Database;
import kz.keste.models.School;
import kz.keste.security.PasswordAuthentication;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolDAOImpl implements SchoolDAO {

    private String registrationQueryPattern = "INSERT INTO schools VALUES(NULL, '%s','%s','%s','%s','%s','%s','%s')";
    private String searchQueryPatternRU = "SELECT id, school_name_ru, school_name_kz, school_name_en, address, phone FROM schools WHERE LOWER(school_name_ru) " +
            "LIKE ?";
    private String searchQueryPatternKZ = "SELECT id, school_name_ru, school_name_kz, school_name_en, address, phone FROM schools WHERE LOWER(school_name_kz) " +
            "LIKE ?";
    private String searchQueryPatternEN = "SELECT id, school_name_ru, school_name_kz, school_name_en, address, phone FROM schools WHERE LOWER(school_name_en) " +
            "LIKE ?";

    @Override
    public void registerSchool(School school) throws Exception {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String registrationQuery = String.format(registrationQueryPattern,school.getSchoolNameRu(),school.getSchoolNameKz(),
                    school.getSchoolNameEn(),school.getAdminEmail(), PasswordAuthentication.getSaltedHash(school.getPassword()),
                    school.getAddress(), school.getPhone());
            statement.execute(registrationQuery);


        statement.close();
        connection.close();
    }


    public boolean checkAvailableEmail(String email) throws SQLException {
        Connection conn = Database.getConnection();
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM schools WHERE email = '" + email+"';");
        conn.close();
        boolean isAvailable = !resultSet.next();
        resultSet.close();
        return isAvailable;
    }

    @Override
    public void changeEmail(String email) {

    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public Map<Integer,School> findByName(String schoolName) throws SQLException {
        Map<Integer, School> results = new HashMap<>();
        String[] searchNames = schoolName.trim().split(" ");
        Connection connection = Database.getConnection();

        for (String name : searchNames) {

            PreparedStatement stmtRU = connection.prepareStatement(searchQueryPatternRU);
            stmtRU.setString(1, "%"+name.toLowerCase()+"%" );

            ResultSet resultSet = stmtRU.executeQuery();

            PreparedStatement stmtKZ = null;
            PreparedStatement stmtEN = null;

            if (!resultSet.next()){
                stmtKZ = connection.prepareStatement(searchQueryPatternKZ);
                stmtKZ.setString(1,  "%"+name.toLowerCase()+"%");
                resultSet = stmtKZ.executeQuery();
            }


            resultSet.beforeFirst();

            if (!resultSet.next()){
                stmtEN = connection.prepareStatement(searchQueryPatternEN);
                stmtEN.setString(1, "%"+name.toLowerCase()+"%");
                resultSet = stmtEN.executeQuery();
            }

            resultSet.beforeFirst();
            while (resultSet.next()) {
                if (results.containsKey(resultSet.getInt("id")))
                    break;
                School school = new School();
                school.setId(resultSet.getInt("id"));
                school.setSchoolNameRu(resultSet.getString("school_name_ru"));
                school.setAddress(resultSet.getString("address"));
                school.setPhone(resultSet.getString("phone"));
                results.put(school.getId(), school);
            }

            stmtRU.close();
            if (stmtKZ != null)
                stmtKZ.close();

            if (stmtEN != null)
                stmtEN.close();

            resultSet.close();
            connection.close();
        }



        return results;
    }
}
