package kz.keste.dao;

import kz.keste.models.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherDAO {

    void addTeacher(Teacher teacher);

    void changePassword(String password);

    List<Map<String,String>> getAllTeachers(int schoolId);

    List<Map<String,String>> getTeachersSubjects(int teacherId);


}
