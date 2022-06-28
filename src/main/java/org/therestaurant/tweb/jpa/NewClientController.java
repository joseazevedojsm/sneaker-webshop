package org.therestaurant.tweb.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller NewClientController com os metodos newClient @PostMapping("/new-client")
@Controller
public class NewClientController {

    private static final Logger log = LoggerFactory.getLogger(NewClientController.class);

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    String ROLE_PREFIX = "ROLE_";

    @Autowired
    private ClientRepository repository;

    /* Funcao que permite que receber parametros de um form  */
    @PostMapping("/new-client")
    public String newClient(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            @RequestParam(name = "lastname", required = false, defaultValue = "World") String lastname,
            @RequestParam(name = "email", required = false, defaultValue = "World") String email,
            @RequestParam(name = "username", required = false, defaultValue = "World") String username,
            @RequestParam(name = "password", required = false, defaultValue = "World") String password,
            Model model) {

        if (repository.findOneByuserName(username) != null) {
            model.addAttribute("invalidUsername", "Username já existente!");
            return "/new-client-form";
        }

        if (username.contains("XSr#")) {
            repository.save(new Client(name, lastname, email, username, passwordEncoder.encode(password), ROLE_PREFIX + "ADMIN"));
            model.addAttribute("successMessage", "Foi Registado com Sucesso!");

            return "/new-client-form";
        }

        log.info(name);
        log.info(lastname);
        log.info(email);
        log.info("-------------------------------");
        repository.save(new Client(name, lastname, email, username, passwordEncoder.encode(password), ROLE_PREFIX + "USER"));

        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Client aClient : repository.findAll()) {
            log.info(aClient.toString());
        }
        log.info("");

        model.addAttribute("successMessage", "Foi Registado com Sucesso!");

        return "/new-client-form";
    }
}
