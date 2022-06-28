package org.therestaurant.tweb.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long product_id;
        private String productSku;
        private String productName;
        private String shortDescription;
        private String productImage;
        private String brandModel;
        private String productCategorie;
        private String price;

		
	protected Product() {}

	public Product(String sku, String productName, String shortDescription, String productImage, String brandModel, String productCategorie,String price) {
		this.productSku = sku;
                this.productName = productName;
                this.shortDescription = shortDescription;
                this.shortDescription = shortDescription;
                this.productImage = productImage;
                this.brandModel = brandModel;
                this.productCategorie = productCategorie;
                this.price = price;
	}

	@Override
	public String toString() {
		return String.format(
				"Produto[id=%d, sku= '%s',productName='%s', shortDescription='%s', productImage='%s' ,brandModel='%s', productCategorie='%s, price='%s']",
				product_id, productSku, productName, shortDescription, productImage, brandModel, productCategorie, price);
	}
        
	public String getProductSku() {
		return productSku;
	}

	public String getProductName() {
		return productName;
	}
        
        public String getShortDescription() {
		return shortDescription;
	}
        
        public String getProductImage() {
		return productImage;
	}
        
        public String getBrandModel() {
		return brandModel;
	}
        
        public String getProductCategorie() {
		return productCategorie;
	}
        
        public String getPrice() {
		return price;
	}
        
}

