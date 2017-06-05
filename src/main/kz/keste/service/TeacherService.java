package kz.keste.service;

import kz.keste.dao.TeacherDAO;
import kz.keste.dao.TeacherDAOImpl;
import kz.keste.models.Teacher;

import java.util.List;
import java.util.Map;

public class TeacherService {

    private TeacherDAO teacherDAO = new TeacherDAOImpl();

    public void addTeacher(Teacher teacher){
        teacherDAO.addTeacher(teacher);
    }

    public List<Map<String,String>> getAllTeachers(int schoolId){
        return teacherDAO.getAllTeachers(schoolId);
    }
}
