package ru.otus.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.dao.crm.model.Client;
import ru.otus.dao.crm.services.client.DBServiceClient;

import java.io.IOException;


public class ApiClient extends HttpServlet {

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final int ID_PATH_PARAM_POSITION = 1;

    private final Gson gson;
    private final DBServiceClient clientService;

    public ApiClient(Gson gson, DBServiceClient clientService) {
        this.gson = gson;
        this.clientService = clientService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = clientService.findById(extractIdFromRequest(req)).orElse(null);

        resp.setContentType(CONTENT_TYPE);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getOutputStream().print(gson.toJson(client));
    }


    private long extractIdFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String id = (path.length > 1)? path[ID_PATH_PARAM_POSITION]: String.valueOf(- 1);
        return Long.parseLong(id);
    }
}
