package org.therestaurant.tweb.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class NewProductController {

    private static final Logger log = LoggerFactory.getLogger(NewProductController.class);

    @Autowired
    private ProductRepository repository;

    @PostMapping("/new-product")
    public String newProduct(
            @RequestParam(name = "productSku", required = false, defaultValue = "World") String productSku,
            @RequestParam(name = "productName", required = false, defaultValue = "World") String productName,
            @RequestParam(name = "shortDescription", required = false, defaultValue = "World") String shortDescription,
            @RequestParam(name = "productImage", required = false, defaultValue = "World") String productImage,
            @RequestParam(name = "brandModel", required = false, defaultValue = "World") String brandModel,
            @RequestParam(name = "productCategorie", required = false, defaultValue = "World") String productCategorie,
            @RequestParam(name = "price", required = false, defaultValue = "World") String price,
            Model model) {
        
        
        if(repository.findByproductSku(productSku)!= null){
            model.addAttribute("invalidSku", "Referência já existente!");
            return "new-product-form";
         }
        
        log.info(productSku);
        log.info(productName);
        log.info(shortDescription);
        log.info(productImage);
        log.info(brandModel);
        log.info(productCategorie);
        log.info(price);
        log.info("-------------------------------");
        repository.save(new Product(productSku, productName, shortDescription, productImage, brandModel, productCategorie, price));

        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Product pProduct : repository.findAll()) {
            log.info(pProduct.toString());
        }
        log.info("");

        model.addAttribute("productSku", productSku);
        model.addAttribute("productName", productName);
        model.addAttribute("shortDescription", shortDescription);
        model.addAttribute("productImage", productImage);
        model.addAttribute("brandModel", brandModel);
        model.addAttribute("productCategorie", productCategorie);
        model.addAttribute("price", price);
        model.addAttribute("successMessage", "Produto adicionado com Sucesso!");
        return "new-product-form";
    }
}
