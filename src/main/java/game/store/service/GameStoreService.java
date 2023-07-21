package game.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.store.controller.model.GameStoreData;
import game.store.controller.model.GameStoreEmployee;
import game.store.dao.EmployeeDao;
import game.store.dao.GameDao;
import game.store.dao.GameStoreDao;
import game.store.entity.Employee;
import game.store.entity.GameStore;

@Service
public class GameStoreService {
	@Autowired
	private GameStoreDao gameStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
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




}
