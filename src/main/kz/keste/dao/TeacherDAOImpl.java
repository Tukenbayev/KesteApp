package kz.keste.dao;

import kz.keste.database.Database;
import kz.keste.models.Subject;
import kz.keste.models.Teacher;
import kz.keste.security.PasswordGenerator;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherDAOImpl implements TeacherDAO {

    private final String ADD_TEACHER_QUERY = "INSERT INTO teachers VALUES(?,?,?,?)";
    private final String ADD_TEACHER_SUBJECTS_QUERY = "INSERT INTO teacher_subject VALUES(?,?)";
    private final String GET_ALL_TEACHERS_QUERY = "SELECT phone, name FROM teachers WHERE school_id = ?";
    private final String GET_TEACHER_SUBJECTS_QUERY = "SELECT ";

    @Override
    public void addTeacher(Teacher teacher) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_TEACHER_QUERY);
            statement.setLong(1, teacher.getPhone());
            statement.setString(2,teacher.getName());
            statement.setString(3, PasswordGenerator.getRandomPassword());
            statement.setLong(4, teacher.getSchoolId());
            statement.execute();

            connection.close();
            statement.close();

            addTeacherSubjects(teacher.getSubjects(),teacher.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addTeacherSubjects(List<Subject> subjects, long phone){
        try {
            Connection connection = Database.getConnection();
            for (Subject subject : subjects){
                PreparedStatement statement = connection.prepareStatement(ADD_TEACHER_SUBJECTS_QUERY);
                statement.setLong(1,phone);
                statement.setInt(2, Math.toIntExact(subject.getId()));
                statement.execute();
                statement.close();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public List<Map<String,String>> getAllTeachers(int schoolId) {
        List<Map<String,String>> teachers = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_TEACHERS_QUERY);
            statement.setInt(1,schoolId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Map<String,String> map = new HashMap<>();
                map.put("teacherId", String.valueOf(resultSet.getLong("phone")));
                map.put("teacherName",resultSet.getString("name"));
                teachers.add(map);
            }

            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public List<Map<String, String>> getTeachersSubjects(int teacherId) {
        List<Map<String,String>> subjects = new ArrayList<>();
        try {
            Connection connection = Database.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
