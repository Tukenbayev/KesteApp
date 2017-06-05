package kz.keste.controllers.actions;


import com.google.gson.Gson;
import kz.keste.service.SchoolService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class CheckEmailAction implements Action {
    private SchoolService service = new SchoolService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean isAvailable = service.checkAvailableEmail(request.getParameter("email"));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, Boolean> map = new HashMap<>();
        map.put("isAvailable",isAvailable);
        write(response,map);
        return null;
    }

    private void write(HttpServletResponse response, Map<String, Boolean> map) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(map));
        writer.close();
    }


}
