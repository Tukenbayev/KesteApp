package kz.keste.controllers;

import kz.keste.controllers.actions.Action;
import kz.keste.controllers.actions.ActionFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/registration","/create_teachers","/search","/register","/check-email","/login","/signin","/school",
        "/success","/create-class","/get-all-classes","/delete-class","/check-teachers-phone","/check-subject",
        "/add-teacher","/get-all-teachers"})
public class FrontControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 102831973239L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Action action = ActionFactory.getAction(request);
            String view = action.execute(request,response);

            if(view != null && view.equals(request.getServletPath().substring(1))) {
                request.getRequestDispatcher("pages/" + view + ".jsp").forward(request, response);
            }
            else if(view != null){
                response.sendRedirect(view);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
