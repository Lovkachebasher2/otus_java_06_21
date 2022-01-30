package ru.otus.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.entity.Client;
import ru.otus.service.ClientService;

@Controller
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/clients")
    public ModelAndView getUsers() {
        return getModel("clients");
    }

    @PostMapping("/clients")
    public ModelAndView createUser(@ModelAttribute("client") Client client) {
        clientService.save(client);
        return getModel("clients");
    }

    @PostMapping("/clients/deleteAll")
    public ModelAndView removeAll() {
        clientService.removeAll();
        return getModel("clients");
    }

    @GetMapping("/clients/deleteById/{id}")
    public ModelAndView removeById(@PathVariable("id") Long id) {
        clientService.remove(id);
        return getModel("clients");
    }

    private ModelAndView getModel(String name) {
        ModelAndView model = new ModelAndView(name);
        model.addObject("client", new Client());
        model.addObject("clients", clientService.findAll());
        return model;
    }
}
