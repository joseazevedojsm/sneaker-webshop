package org.therestaurant.tweb.jpa;

import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Controller AdminController 
@Controller
public class UserDashController {

    private static final Logger log = LoggerFactory.getLogger(UserDashController.class);

    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private OrderRepository2 orderepository;

    @Autowired
    private ClientRepository userRepository;
    
    @GetMapping("/usersdash")
    public void userDashboard(Model model, Principal principal) {
        
        String userName = principal.getName();
        
        Client client = userRepository.findOneByuserName(userName);
        
        List<Ordeer2> userordeer = (List<Ordeer2>) orderepository.findByClient(client);

        model.addAttribute("userordeer", userordeer);
        
    } 
}
