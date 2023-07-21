package game.store.controller.model;

import game.store.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameStoreEmployee {

	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeePhoneNumber;
	public GameStoreEmployee (Employee employee) {
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeePhoneNumber = employee.getEmployeePhoneNumber();
	}
}
