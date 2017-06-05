import com.google.gson.Gson;
import kz.keste.service.SchoolService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Map<String,String>> teachers = new ArrayList<>();
        for (int i = 0; i<5;i++){
            Map<String,String> map = new HashMap<>();
            map.put("teacherId","87772061318");
            map.put("teacherName","Rustemova R.A.");
            teachers.add(map);
        }

        Gson gson = new Gson();
        System.out.println(gson.toJson(teachers));
    }
}
