package kz.keste.controllers.actions;

import com.google.gson.Gson;
import kz.keste.models.Subject;
import kz.keste.models.Teacher;
import kz.keste.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTeacherAction implements Action{

    private TeacherService teacherService = new TeacherService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Gson gson = new Gson();
        System.out.println(request.getParameter("teacher"));
        Teacher teacher = gson.fromJson(request.getParameter("teacher"), Teacher.class);
        if (teacher != null)
            teacherService.addTeacher(teacher);

        return null;
    }
}
