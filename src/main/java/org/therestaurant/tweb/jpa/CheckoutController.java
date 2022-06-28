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
import org.springframework.web.context.request.WebRequest;

@Controller
public class CheckoutController {

    private static final Logger log = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    private ClientRepository repository;

    @Autowired
    private OrderRepository2 orderrepository;

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session, Principal principal, WebRequest request) {

        if (session.getAttribute("cart") != null) {

            List<Product> cart = (List<Product>) session.getAttribute("cart");
            
            if (!cart.isEmpty()) {
                log.info("Entrou!");

                Client client = repository.findOneByuserName(principal.getName());
                
                List<Ordeer2> orders = (List<Ordeer2>) orderrepository.findAll();

                if (!orders.isEmpty()) {
                    
                    log.info("Entrou nas orders!");

                    List<Ordeer2> ordeer2 = (List<Ordeer2>) orderrepository.findAll();

                    Ordeer2 ordeer = ordeer2.get(ordeer2.size() - 1);

                    String idOrder = ordeer.generateNextIdOrder(ordeer.getIdOrder());

                    for (int i = 0; i < cart.size(); ++i) {
                        String productName = cart.get(i).getProductName();
                        String productSku = cart.get(i).getProductSku();
                        String price = cart.get(i).getPrice();

                        Ordeer2 neworder = new Ordeer2(idOrder, productName, productSku, price, client);
                        client.saveOrder(neworder);
                        orderrepository.save(neworder);
                        model.addAttribute("idOrder", idOrder);
                        model.addAttribute("cart2", cart);
                        model.addAttribute("successMessage", "Encomenda Realizada com Sucesso!");
                        request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
                    }

                } else {

                    for (int k = 0; k < cart.size(); ++k) {
                        String productName = cart.get(k).getProductName();
                        String productSku = cart.get(k).getProductSku();
                        String price = cart.get(k).getPrice();

                        Ordeer2 neworder = new Ordeer2("3000", productName, productSku, price, client);
                        client.saveOrder(neworder);
                        orderrepository.save(neworder);
                        model.addAttribute("idOrder", "3000");
                        model.addAttribute("cart2", cart);
                        model.addAttribute("successMessage", "Encomenda Realizada com Sucesso!");
                        request.removeAttribute("cart", WebRequest.SCOPE_SESSION);
                    }
                }
                return "checkout-view";
            }
            model.addAttribute("invalidCheckout", "Carrinho Vazio!");
            return "/carrinho";
        }
        model.addAttribute("invalidCheckout", "Carrinho Vazio!");
        return "/carrinho";
    }
}
