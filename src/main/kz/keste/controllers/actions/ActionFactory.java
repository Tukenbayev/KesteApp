package kz.keste.controllers.actions;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private static Map<String,Action> actionMap = new HashMap<>();

    static {
        actionMap.put("GET/register", new RegisterAction());
        actionMap.put("GET/registration", new RedirectAction());
        actionMap.put("POST/search", new SearchAction());
        actionMap.put("POST/check-email", new CheckEmailAction());
        actionMap.put("GET/signin", new RedirectAction());
        actionMap.put("GET/login", new LoginAction());
        actionMap.put("GET/school", new CheckAdminAction());
        actionMap.put("GET/success", new RedirectAction());
        actionMap.put("POST/create-class", new CreateClassAction());
        actionMap.put("POST/get-all-classes", new GetAllClassesAction());
        actionMap.put("GET/delete-class", new DeleteClassAction());
        actionMap.put("POST/check-teachers-phone", new CheckTeachersPhoneAction());
        actionMap.put("POST/check-subject", new CheckSubjectAction());
        actionMap.put("POST/add-teacher", new AddTeacherAction());
        actionMap.put("GET/get-all-teachers", new GetAllTeachersAction());
    }

    public static Action getAction(HttpServletRequest request){
        return actionMap.get(request.getMethod()+request.getServletPath());
    }


}
