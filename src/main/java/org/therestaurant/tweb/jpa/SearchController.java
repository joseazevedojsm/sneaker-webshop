package org.therestaurant.tweb.jpa;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

//Controller SearchController 
@Controller
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ClientRepository userRepository;

    @PostMapping("/search-product")
    public RedirectView searchProduct(
            @RequestParam(name = "search", required = false, defaultValue = "World") String name,
            Model model, Principal principal, HttpSession session, WebRequest request) {

        log.info(name);
        log.info("-------------------------------");

        List<Product> productList = (List<Product>) repository.findByproductNameContaining(name.toUpperCase());
        List<String> categorieList = new ArrayList<String>();

        for (int i = 0; i < productList.size(); i++) {
            if (categorieList.contains(productList.get(i).getProductCategorie())) {

            } else {
                categorieList.add(productList.get(i).getProductCategorie());
            }
        }

        if (productList.size() == 1) {
            request.removeAttribute("singleP", WebRequest.SCOPE_SESSION);

            Product singleP = repository.findByproductSku(productList.get(0).getProductSku());

            session.setAttribute("singleP", singleP);

            if (principal != null) {
                return new RedirectView("/psingle-product-view");
            }
            return new RedirectView("/single-product-view");
        } else if (productList.isEmpty()) {
            if (principal != null) {
                return new RedirectView("/home");
            }
            log.info("-------------------------------");
            return new RedirectView("/");
        }
        request.removeAttribute("searchP", WebRequest.SCOPE_SESSION);
        request.removeAttribute("categories", WebRequest.SCOPE_SESSION);

        session.setAttribute("productList", productList);
        session.setAttribute("categorieList", categorieList);

        if (principal != null) {
            return new RedirectView("/psearch-product-view");
        }
        return new RedirectView("/search-product-view");
    }

    @GetMapping("/search-product/{categorie}")
    public RedirectView searchByCategorie(@PathVariable("categorie") String categorie, Model model, HttpSession session, WebRequest request, Principal principal) {
        log.info(categorie);

        List<Product> productList = (List<Product>) repository.findByproductCategorie(categorie);
        List<String> categorieList = new ArrayList<String>();

        for (int i = 0; i < productList.size(); i++) {
            if (categorieList.contains(productList.get(i).getProductCategorie())) {

            } else {
                categorieList.add(productList.get(i).getProductCategorie());
            }
        }

        request.removeAttribute("searchP", WebRequest.SCOPE_SESSION);
        request.removeAttribute("categories", WebRequest.SCOPE_SESSION);

        session.setAttribute("productList", productList);
        session.setAttribute("categorieList", categorieList);

        if (principal != null) {
            return new RedirectView("/psearch-product-view");
        }

        return new RedirectView("/search-product-view");
    }

    @GetMapping("/single-product/{productSku}")
    public RedirectView detailsSingleProduct(@PathVariable("productSku") String productSku, Model model, HttpSession session, WebRequest request, Principal principal) {
        log.info(productSku);

        request.removeAttribute("singleP", WebRequest.SCOPE_SESSION);

        Product singleP = repository.findByproductSku(productSku);

        session.setAttribute("singleP", singleP);

        if (principal != null) {
            return new RedirectView("/psingle-product-view");
        }

        return new RedirectView("/single-product-view");
    }

    @GetMapping("/adsearch-form/form")
    public RedirectView adsearchForm(Model model, HttpSession session, WebRequest request) {

        AdSearch adsearch = new AdSearch();

        List<Product> productList2 = (List<Product>) repository.findAll();
        List<String> categorieList = new ArrayList<String>();

        for (int i = 0; i < productList2.size(); i++) {
            if (categorieList.contains(productList2.get(i).getProductCategorie())) {

            } else {
                categorieList.add(productList2.get(i).getProductCategorie());
            }
        }

        model.addAttribute("adsearch", adsearch);
        session.setAttribute("categorieList", categorieList);
        return new RedirectView("/adsearch-form");
    }

    @RequestMapping("/adsearch-form")
    public void submitForm(@ModelAttribute("adsearch") AdSearch adsearch) {
        if (adsearch.getShortDescription() != null) {
            log.info(adsearch.getShortDescription());
            log.info(adsearch.getRangePrice());
        }
    }
    
    @GetMapping("/padsearch-form/form")
    public RedirectView adsearchForm2(Model model, HttpSession session, WebRequest request) {

        AdSearch adsearch = new AdSearch();

        List<Product> productList2 = (List<Product>) repository.findAll();
        List<String> categorieList = new ArrayList<String>();

        for (int i = 0; i < productList2.size(); i++) {
            if (categorieList.contains(productList2.get(i).getProductCategorie())) {

            } else {
                categorieList.add(productList2.get(i).getProductCategorie());
            }
        }

        model.addAttribute("adsearch", adsearch);
        session.setAttribute("categorieList", categorieList);
        return new RedirectView("/padsearch-form");
    }

    @RequestMapping("/padsearch-form")
    public void submitForm2(@ModelAttribute("adsearch") AdSearch adsearch) {
        if (adsearch.getShortDescription() != null) {
            log.info(adsearch.getShortDescription());
            log.info(adsearch.getRangePrice());
        }

    }

    /* Funcao que permite que receber parametros de um form  
    @PostMapping("/adsearch-product")
    public String newClient(
            @RequestParam(name = "shortDescription", required = false, defaultValue = "World") String shortDescription,
            @RequestParam(name = "lastname", required = false, defaultValue = "World") String lastname,
            @RequestParam(name = "email", required = false, defaultValue = "World") String email,
            @RequestParam(name = "username", required = false, defaultValue = "World") String username,
            @RequestParam(name = "password", required = false, defaultValue = "World") String password,
            Model model) {

        /*if (repository.findOneByuserName(username) != null) {
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


        model.addAttribute("successMessage", "Foi Registado com Sucesso!");
        return "/new-client-form";
    }*/
}
