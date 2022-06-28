package org.therestaurant.tweb.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

//Controller CartController 
@Controller
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);
    
    @Autowired
    private ProductRepository repository;

    @GetMapping("/remove-cart/{productSku}")
    public RedirectView removeProduct(@PathVariable("productSku") String productSku, HttpSession session, WebRequest request) {
        log.info(productSku);

        List<Product> cart = (List<Product>) session.getAttribute("cart");
        
        List<Product> cartfinal = new ArrayList();
        
        for(Product product : cart) {
            if (product.getProductSku().equals(productSku)) {
                continue;
            }
            cartfinal.add(product);
        }
        
        if(cartfinal.isEmpty()){
            request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
            return new RedirectView("/carrinho");
        }
        
        request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
        session.setAttribute("cart", cartfinal);

        return new RedirectView("/carrinho");
    }
    
    @GetMapping("/add-cart/{productSku}")
    public RedirectView addProductCart(@PathVariable("productSku") String productSku, HttpSession session, Model model) {
        log.info(productSku);

        Product product = repository.findByproductSku(productSku);

        if (session.getAttribute("cart") == null) {
            List<Product> cart = new ArrayList<Product>();
            cart.add(product);
            log.info(Integer.toString(cart.size()));
            session.setAttribute("cart", cart);

        } else {
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            cart.add(product);
            log.info(Integer.toString(cart.size()));
            session.setAttribute("cart", cart);
        }

        return new RedirectView("/carrinho");
    }
}
