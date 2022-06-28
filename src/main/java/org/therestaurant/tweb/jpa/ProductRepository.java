package org.therestaurant.tweb.jpa;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
        // Pesquisa pelo nome e constroi lista de produtos
        List<Product> findByproductName(String productName);
        
        // Pesquisa pelo sku do produto
        Product findByproductSku(String productSku);
        
        // Pesquisa pelos produtos que tenham o atributo name no seu nome e constroi uma lista de produtos que cumpram essa mesma regra
        List<Product> findByproductNameContaining(String name);
        
        // Pesquisa pelos produtos que tenham a mesma categoria que o atributo categorie
        List<Product> findByproductCategorie(String categorie);
        
        // Ter acesso a remover o produto da BD, acesso pelo SKU = Referência do Produto
        @Transactional
        void deleteByproductSku(String productSku);
        
}
