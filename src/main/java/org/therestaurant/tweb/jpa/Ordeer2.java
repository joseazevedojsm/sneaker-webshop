package org.therestaurant.tweb.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ordeer2 {
    
    	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
        private String idOrder;
	private String productName;
        private String productSku;
        private String price;
        @ManyToOne
        @JoinColumn(name="client_id")
        private Client client;
		
	protected Ordeer2() {}

	public Ordeer2(String idOrder, String productName, String productSku, String price, Client client) {
                this.idOrder = idOrder;
                this.productName = productName;
                this.productSku = productSku;
                this.price = price;
                this.client = client;
	}

	@Override
	public String toString() {
		return String.format(
				"Order[id=%d, ,idOrder='%s' ,productName='%s', productSku='%s',  price='%s',client='%s'']",
				id, idOrder, productName, productSku, price, client);
	}
        
        public String getIdOrder() {
		return idOrder;
	}
        
        public String generateNextIdOrder(String idOrder) {
                
                int id = Integer.parseInt(idOrder)+1;
		return String.valueOf(id);
	}
        
        public String getProductName() {
		return productName;
	}

	public String getProductSku() {
		return productSku;
	}
        
        public String getPrice() {
		return price;
	}
        
        public String getUserId() {
		return Long.toString(client.getUserId());
	}
        
        public String getClient_name(){
            return client.getFirstName();
        }
        
         public String getClient_userName(){
            return client.getUserName();
        }
}
