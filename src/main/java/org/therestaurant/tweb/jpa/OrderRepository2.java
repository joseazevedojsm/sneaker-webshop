package org.therestaurant.tweb.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository2 extends CrudRepository<Ordeer2, Long> {

	List<Ordeer2> findByClient(Client client);
	
}
