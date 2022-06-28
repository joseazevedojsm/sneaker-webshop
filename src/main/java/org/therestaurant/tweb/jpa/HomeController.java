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
import org.springframework.web.bind.annotation.RequestMapping;

//Controller HomeController define o conteudo que irá ser mostrada em cada homepage quer seja publica quer seja privada
@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductRepository repository;

    @RequestMapping("/")
    public String home() {
        return "/index";
    }
    
    @GetMapping("/")
    public String listProduct1(Model model) {

        List<Product> productList2 = (List<Product>) repository.findAll();
        List<String> categorieList = new ArrayList<String>();

        for (int i = 0; i < productList2.size(); i++) {
            if (categorieList.contains(productList2.get(i).getProductCategorie())) {

            } else {
                categorieList.add(productList2.get(i).getProductCategorie());
            }
        }

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : repository.findAll()) {
            log.info(product.toString());
        }
        log.info("");
        for (int i = 0; i < categorieList.size(); i++) {
            log.info(categorieList.get(i).toString());
        }
        log.info("");

        model.addAttribute("productList2", productList2);
        model.addAttribute("categorieList", categorieList);
        
        return "/index";
    }

    @GetMapping("/index")
    public void listProduct2(Model model) {

        List<Product> productList2 = (List<Product>) repository.findAll();
        List<String> categorieList = new ArrayList<String>();

        for (int i = 0; i < productList2.size(); i++) {
            if (categorieList.contains(productList2.get(i).getProductCategorie())) {

            } else {
                categorieList.add(productList2.get(i).getProductCategorie());
            }
        }

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : repository.findAll()) {
            log.info(product.toString());
        }
        log.info("");
        for (int i = 0; i < categorieList.size(); i++) {
            log.info(categorieList.get(i).toString());
        }
        log.info("");

        model.addAttribute("productList2", productList2);
        model.addAttribute("categorieList", categorieList);
    }

    @GetMapping("/home")
    public void listProduct3(Model model, HttpSession session, Principal principal) {

        List<Product> productList2 = (List<Product>) repository.findAll();
        List<String> categorieList = new ArrayList<String>();

        for (int i = 0; i < productList2.size(); i++) {
            if (categorieList.contains(productList2.get(i).getProductCategorie())) {

            } else {
                categorieList.add(productList2.get(i).getProductCategorie());
            }
        }

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : repository.findAll()) {
            log.info(product.toString());
        }
        log.info("");

        model.addAttribute("productList2", productList2);
        model.addAttribute("categorieList", categorieList);
        session.setAttribute("userName", principal.getName());
    }
}
