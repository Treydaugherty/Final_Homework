package game.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import game.store.entity.Customer;
import game.store.entity.Employee;
import game.store.entity.Game;
import game.store.entity.GameStore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameStoreData {
	private Long gameStoreId;
	private String gameStoreName;
	private String address;
	private String phoneNumber;
	private String city;
	private String state;
	private Set<GameStoreCustomer> customers = new HashSet<>();
	private Set<GameStoreEmployee> employees = new HashSet<>();
	private Set<GameStoreGame> games = new HashSet<>();

	public GameStoreData (GameStore gameStore) {
		gameStoreId = gameStore.getGameStoreId();
		gameStoreName = gameStore.getGameStoreName();
		address = gameStore.getAddress();
		phoneNumber = gameStore.getAddress();
		city = gameStore.getCity();
		state = gameStore.getState();
		
		for(Customer customer : gameStore.getCustomers()) {
			customers.add(new GameStoreCustomer(customer));
		}
		
		for(Employee employee : gameStore.getEmployees()) {
			employees.add(new GameStoreEmployee(employee));
			
		for(Game game : gameStore.getGames()) {
			games.add(new GameStoreGame(game));
		}
		}
	}
}
