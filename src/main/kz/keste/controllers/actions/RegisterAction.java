package kz.keste.controllers.actions;

import kz.keste.models.School;
import kz.keste.service.SchoolService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {

    private SchoolService schoolService = new SchoolService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        School school = new School();
        school.setSchoolNameRu(request.getParameter("schoolNameRU"));
        school.setSchoolNameKz(request.getParameter("schoolNameKZ"));
        school.setSchoolNameEn(request.getParameter("schoolNameEN"));
        school.setAdminEmail(request.getParameter("adminEmail"));
        school.setAddress(request.getParameter("address"));
        school.setPassword(request.getParameter("password"));
        school.setPhone(request.getParameter("phone"));
        school.setAdminEmail(request.getParameter("email"));

        schoolService.registerSchool(school);

        return "success";
    }
}
