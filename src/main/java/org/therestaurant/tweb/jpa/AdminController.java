package org.therestaurant.tweb.jpa;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

//Controller AdminController 
@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private OrderRepository2 orderepository;
    
    @GetMapping("/admin")
    public void adminName(HttpSession session, Principal principal) {

        session.setAttribute("userName", principal.getName());   
    }

    @GetMapping("/delete-page")
    public void listProduct(Model model) {

        List<Product> productList3 = (List<Product>) repository.findAll();

        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : repository.findAll()) {
            log.info(product.toString());
        }
        log.info("");

        model.addAttribute("productList3", productList3);
    }
    
    @GetMapping("/adminlist")
    public void listProduct2(Model model) {

        List<Product> productList = (List<Product>) repository.findAll();

        model.addAttribute("productList", productList); 
    }

    @GetMapping("/delete-page/{productSku}")
    public RedirectView removeProduct(@PathVariable("productSku") String productSku) {
        log.info(productSku);

        repository.deleteByproductSku(productSku);

        return new RedirectView("/delete-page");
    }
    
    @GetMapping("/list-orders")
    public void listOrders(Model model) {
        
        List<Ordeer2> listOrders = (List<Ordeer2>) orderepository.findAll();

        model.addAttribute("listOrders", listOrders);
    }
}
