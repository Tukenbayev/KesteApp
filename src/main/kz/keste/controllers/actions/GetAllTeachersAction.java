package kz.keste.controllers.actions;

import com.google.gson.Gson;
import kz.keste.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class GetAllTeachersAction implements Action {

    private TeacherService teacherService = new TeacherService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int schoolId = Integer.parseInt(request.getParameter("schoolId"));
        List<Map<String,String>> teachers = teacherService.getAllTeachers(schoolId);
        write(teachers,response);

        return null;
    }

    private void write(List<Map<String,String>> teachers, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(new Gson().toJson(teachers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
