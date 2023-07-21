package game.store.controller.model;


import game.store.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameStoreCustomer {

	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
	public GameStoreCustomer(Customer customer) {
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
	}
}
