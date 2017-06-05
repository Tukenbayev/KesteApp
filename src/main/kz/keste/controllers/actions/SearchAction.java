package kz.keste.controllers.actions;

import com.google.gson.Gson;
import kz.keste.models.School;
import kz.keste.service.SchoolService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class SearchAction implements Action {

    private SchoolService schoolService = new SchoolService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Integer,School> searchResults = schoolService.findByName(request.getParameter("searchName"));
        write(response, searchResults);

        return null;
    }

    private void write(HttpServletResponse response, Map<Integer, School> searchResults) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(searchResults));
        writer.close();
    }
}
