package kz.keste.controllers.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckAdminAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("check admin action ! "+session.getAttribute("username"));
        if (session.getAttribute("username") != null)
            return "school";
        return "signin";
    }
}
