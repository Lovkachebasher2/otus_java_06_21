package ru.otus.servlet;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.dao.crm.service.DBServiceUser;
import ru.otus.model.User;
import ru.otus.services.TemplateProcessor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UsersServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String TEMPLATE_ATTR_RANDOM_USER = "users";

    private final TemplateProcessor templateProcessor;
    private final DBServiceUser userService;
    private final Gson gson;

    public UsersServlet(TemplateProcessor templateProcessor, DBServiceUser userService, Gson gson) {
        this.templateProcessor = templateProcessor;
        this.userService = userService;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        List<User> userList = userService.findAll();
        paramsMap.put(TEMPLATE_ATTR_RANDOM_USER, userList);
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = gson.fromJson(req.getReader(), User.class);
        userService.save(user);
    }


}
