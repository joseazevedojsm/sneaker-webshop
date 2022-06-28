package org.therestaurant.tweb.jpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

//Controller Login_LogoutController com metodos login @GetMapping("/login"), register @GetMapping("/new-client-form") e logoutPage @GetMapping("/logout") 
@Controller
public class LoginController {

    /* Funcao login @GetMapping("/login") que verifica se existe um principal(algum utilizador logged), se sim redireciona a pagina home, 
    caso contrario vai para a pagina de login */
    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "/login";
    }
    
    /* Funcao register @GetMapping("/new-client-form") que verifica se existe um principal(algum utilizador logged), se sim redireciona a pagina home, 
    caso contrario vai para a pagina de new-client-form */
    @GetMapping("/new-client-form")
    public String register(Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "/new-client-form";
    }
    
    /* Funcao logoutPage @GetMapping("/logout") que verifica se existe uma Authentication, se sim faz logout, 
    caso contrario vai para a pagina de index */
    @GetMapping("/logout") 
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  
}