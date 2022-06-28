 package org.therestaurant.tweb.jpa;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
        private String userName;
        private String passWord;
        private String role;
	private String firstName;
        private String lastName;
        private String email;
        
        @OneToMany(mappedBy = "client")
        private List<Ordeer2> Order;
		
	protected Client() {}

	public Client(String firstName,String lastName, String email, String username, String password, String role) {
		this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.passWord = password;
                this.userName = username;
                this.role = role;
	}

	@Override
	public String toString() {
		return String.format(
				"Client[id=%d, firstName='%s', lastName='%s', email='%s']",
				userId, firstName,lastName,email);
	}

        public void saveOrder(Ordeer2 order){
            this.Order.add(order);
        }
        
	public Long getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}
        
        public String getLastName() {
		return lastName;
	}
        
        public String getEmail() {
		return email;
	}

        public String getUserName() {
		return userName;
	}
        
        public void setUserName(String username) {
		this.userName = username;
	}
        
        public String getPassWord() {
		return passWord;
	}
        
        public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
        
        public String getRole() {
		return role;     
	}
        
        public void setRole(String role) {
		this.role = role;
	}
        
}

