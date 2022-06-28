package org.therestaurant.tweb.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/new-client-form").setViewName("new-client-form");
        registry.addViewController("/new-order-form").setViewName("new-order-form");
        registry.addViewController("/new-product-form").setViewName("new-product-form");
        registry.addViewController("/delete-page").setViewName("delete-page");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/carrinho").setViewName("carrinho");
        registry.addViewController("/userdash").setViewName("userdash");
        registry.addViewController("/list-orders").setViewName("list-orders");
        registry.addViewController("/adminlist").setViewName("adminlist");
        registry.addViewController("/adsearch-form").setViewName("adsearch-form");
        registry.addViewController("/padsearch-form").setViewName("padsearch-form");
        registry.addViewController("/psingle-product-view").setViewName("psingle-product-view");
        registry.addViewController("/single-product-view").setViewName("single-product-view");
        registry.addViewController("/search-product-view").setViewName("search-product-view");
        registry.addViewController("/psearch-product-view").setViewName("psearch-product-view");
    }
}