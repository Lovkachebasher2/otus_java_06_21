package ru.otus.servlet;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.dao.crm.model.Client;
import ru.otus.dao.crm.model.User;
import ru.otus.dao.crm.services.TemplateProcessor;
import ru.otus.dao.crm.services.client.DBServiceClient;
import ru.otus.dao.crm.services.user.DBServiceUser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientServlet extends HttpServlet {

    private static final String CLIENTS_PAGE_TEMPLATE = "clients.html";
    private static final String TEMPLATE_ATR_CLIENT_LIST = "clients";

    private final TemplateProcessor templateProcessor;
    private final DBServiceClient clientService;
    private final Gson gson;

    public ClientServlet(TemplateProcessor templateProcessor, DBServiceClient clientService, Gson gson) {
        this.templateProcessor = templateProcessor;
        this.clientService = clientService;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        List<Client> clientList = clientService.findAll();
        paramsMap.put(TEMPLATE_ATR_CLIENT_LIST, clientList);
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(CLIENTS_PAGE_TEMPLATE, paramsMap));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = gson.fromJson(req.getReader(), Client.class);
        clientService.save(client);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }


}
