package game.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.store.controller.model.GameStoreCustomer;
import game.store.controller.model.GameStoreData;
import game.store.controller.model.GameStoreEmployee;
import game.store.controller.model.GameStoreGame;
import game.store.dao.CustomerDao;
import game.store.dao.EmployeeDao;
import game.store.dao.GameDao;
import game.store.dao.GameStoreDao;
import game.store.entity.Customer;
import game.store.entity.Employee;
import game.store.entity.Game;
import game.store.entity.GameStore;

@Service
public class GameStoreService {
	@Autowired
	private GameStoreDao gameStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private GameDao gameDao;
	
	//GameStore methods
	
	@Transactional(readOnly = false)
	public GameStoreData saveGameStore(GameStoreData gameStoreData) {
		Long gameStoreId = gameStoreData.getGameStoreId();
		GameStore gameStore = findOrCreateGameStore(gameStoreId);
		
		copyGameStoreFields(gameStore, gameStoreData);
		return new GameStoreData(gameStoreDao.save(gameStore));
	}
	
	private GameStore findStoreById(Long gameStoreId) {
		return gameStoreDao.findById(gameStoreId)
				.orElseThrow(() -> new NoSuchElementException
						("GameStore with ID=" + gameStoreId + " was not found."));		
	}
	@Transactional(readOnly = false)
	public List<GameStoreData> retriveAllGameStores() {
		List<GameStore> gameStores = gameStoreDao.findAll();
		List<GameStoreData> result = new LinkedList<>();
		
		for(GameStore gameStore : gameStores) {
			GameStoreData gsd = new GameStoreData(gameStore);
				gsd.getCustomers().clear();
				gsd.getEmployees().clear();
				gsd.getGames().clear();
				result.add(gsd);
		}
		return result;
	}
	
	@Transactional(readOnly = true)
	public GameStoreData retrieveGameStoreById(Long gameStoreId) {
		
		return new GameStoreData(findStoreById(gameStoreId));
	}
	
	private void copyGameStoreFields(GameStore gameStore, GameStoreData gameStoreData) {
		gameStore.setGameStoreId(gameStoreData.getGameStoreId());
		gameStore.setGameStoreName(gameStoreData.getGameStoreName());
		gameStore.setAddress(gameStoreData.getAddress());
		gameStore.setPhoneNumber(gameStoreData.getPhoneNumber());
		gameStore.setCity(gameStoreData.getCity());
		gameStore.setState(gameStoreData.getState());
	}

	private GameStore findOrCreateGameStore(Long gameStoreId) {
		GameStore gameStore;
		if (Objects.isNull(gameStoreId)) {
			gameStore = new GameStore();
		} else {
			gameStore = findStoreById(gameStoreId);
		}
		return gameStore;
	}
	
	@Transactional(readOnly = false)
	public void deleteGameStoreById(Long gameStoreId) {
		GameStore gameStore = findStoreById(gameStoreId);
		gameStoreDao.delete(gameStore);
	}
	//Employee methods
	
	@Transactional(readOnly = false)
	public GameStoreEmployee saveEmployee(Long gameStoreId, 
			GameStoreEmployee gameStoreEmployee) {
		GameStore gameStore = findStoreById(gameStoreId);
		Long employeeId = gameStoreEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(gameStoreId, employeeId);
		
		copyEmployeeFields (employee, gameStoreEmployee);
		employee.setGameStore(gameStore);
		gameStore.getEmployees().add(employee);
		
		Employee dbEmployee = employeeDao.save(employee);
			return new GameStoreEmployee(dbEmployee);
	}

	private void copyEmployeeFields(Employee employee, GameStoreEmployee 
			gameStoreEmployee) {
		employee.setEmployeeId(gameStoreEmployee.getEmployeeId());
		employee.setEmployeeFirstName(gameStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeLastName(gameStoreEmployee.getEmployeeLastName());
		employee.setEmployeePhoneNumber(gameStoreEmployee.getEmployeePhoneNumber());
	}

	private Employee findOrCreateEmployee(Long gameStoreId, Long employeeId) {
		if(Objects.isNull(employeeId)) {
			return new Employee();
		}else {
			System.out.println("find " + employeeId);
			return findEmployeeById(employeeId);
		}
	}

	private Employee findEmployeeById(Long employeeId) {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(() ->
			new NoSuchElementException("Employee with ID= " + employeeId + " does not exist."));
		return employee;
	}

	public void deleteEmployeeById( Long employeeId){
		System.out.println("Delete employee = " + employeeId);
				Employee dbEmployee = findEmployeeById(employeeId); 
				employeeDao.deleteById(dbEmployee.getEmployeeId());
	}

	@Transactional(readOnly = true)
	public GameStoreEmployee retrieveEmployeeById(Long employeeId) {
		
		return new GameStoreEmployee(findEmployeeById(employeeId));
	}
	
	//Customer methods

	@Transactional(readOnly = false)
	public GameStoreCustomer saveCustomer(GameStoreCustomer gameStoreCustomer) {
		Long customerId = gameStoreCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(customerId);
		
		copyCustomerFields(customer, gameStoreCustomer);
		return new GameStoreCustomer(customerDao.save(customer));
	}
		private void copyCustomerFields(Customer customer, GameStoreCustomer gameStoreCustomer) {
			customer.setCustomerFirstName(gameStoreCustomer.getCustomerFirstName());
			customer.setCustomerLastName(gameStoreCustomer.getCustomerLastName());
		
	}

		private Customer findOrCreateCustomer(Long customerId) {
			Customer customer;
		
		if(Objects.isNull(customerId)) {
			customer = new Customer();
		}else { 
			customer = findCustomerById(customerId);
		}
		return customer;
	}

	private Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId).orElseThrow(() -> 
			new NoSuchElementException("Customer with ID=" + customerId + 
					" was not found"));
	}
	
	//Game methods
	
	@Transactional(readOnly = false)
	public GameStoreGame saveGame(GameStoreGame gameStoreGame) {
		Long gameId = gameStoreGame.getGameId();
		Game game = findOrCreateGame(gameId);
		
		copyGameFields(game, gameStoreGame);
		return new GameStoreGame(gameDao.save(game));
	}
		private void copyGameFields(Game game, GameStoreGame gameStoreGame) {
			game.setGameName(gameStoreGame.getGameName());
			game.setGamePrice(gameStoreGame.getGamePrice());
			game.setGameConsole(gameStoreGame.getGameConsole());
			game.setNumberOfCopies(gameStoreGame.getNumberOfCopies());
	}

		private Game findOrCreateGame(Long gameId) {
			Game game;
		
		if(Objects.isNull(gameId)) {
			game = new Game();
		}else { 
			game = findGameById(gameId);
		}
		return game;
	}

	private Game findGameById(Long gameId) {
		return gameDao.findById(gameId).orElseThrow(() -> 
			new NoSuchElementException("Game with ID=" + gameId + 
					" was not found"));
	}

	@Transactional(readOnly = true)
	public GameStoreGame retrieveGameById(Long gameId) {
		
		return new GameStoreGame(findGameById(gameId));
	}
	
	public void deleteGameById( Long gameId){
		System.out.println("Delete game = " + gameId);
				Game dbGame = findGameById(gameId); 
				gameDao.deleteById(dbGame.getGameId());
	}
}
