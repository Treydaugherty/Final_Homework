package game.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import game.store.controller.model.GameStoreCustomer;
import game.store.controller.model.GameStoreData;
import game.store.controller.model.GameStoreEmployee;
import game.store.controller.model.GameStoreGame;
import game.store.service.GameStoreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/game_store")
@Slf4j
public class GameStoreController {
	@Autowired
	private GameStoreService gameStoreService;
	
	//EndPoints for store
	
	@PostMapping("/game_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GameStoreData makeStoreData
		(@RequestBody GameStoreData gameStoreData) {
		log.info("Creating GameStore {}", gameStoreData);
		return gameStoreService.saveGameStore(gameStoreData);
	}
	
	@GetMapping("/game_store")
	public List <GameStoreData> retrieveAllGameStores(){
		log.info("Retrieving all GameStores");
		return gameStoreService.retriveAllGameStores();
	}
	
	@GetMapping("/game_store/{gameStoreId}")
	public GameStoreData retrieveGameStoreById(@PathVariable Long gameStoreId) {
	log.info("Retrieving GameStore with ID= ", gameStoreId);
	return gameStoreService.retrieveGameStoreById(gameStoreId);
	}
	
	@PutMapping("/game_store/{gameStoreId}")
	public GameStoreData updateGameStore(@PathVariable Long gameStoreId,
		@RequestBody GameStoreData gameStoreData) {
			gameStoreData.setGameStoreId(gameStoreId);
		log.info("Updating GameStore {}", gameStoreData);
		return gameStoreService.saveGameStore(gameStoreData);
	}
	
	@DeleteMapping("/game_store/{gameStoreId}")
	public Map<String, String> deleteGameStoreById(@PathVariable Long gameStoreId) {
		log.info("Deleting GameStore with ID= {}", gameStoreId);
		 gameStoreService.deleteGameStoreById(gameStoreId);
		 return Map.of("message","deletion is successful " + gameStoreId);
	}
	
	//EndPoints for employee
	
	@PostMapping("/game_store/{gameStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
    public GameStoreEmployee newStoreEmployee
    (@PathVariable Long gameStoreId, @RequestBody 
    		GameStoreEmployee gameStoreEmployee) {
		log.info("Creating employee {} for game store with ID={}");
		return gameStoreService.saveEmployee(gameStoreId, gameStoreEmployee);
	}
	
	@GetMapping("/game_store/employee/{employeeId}")
	public GameStoreEmployee retrieveEmployeeById(@PathVariable Long employeeId) {
	log.info("Retrieving employee with ID= ", employeeId);
	return gameStoreService.retrieveEmployeeById(employeeId);
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public Map<String, String> deleteEmployeeById(@PathVariable Long employeeId){
		log.info("Deleting GameStore employee with ID= {}", employeeId);
		gameStoreService.deleteEmployeeById(employeeId);
		return Map.of("message", "deletion is successful " + employeeId);
	}
	
	//EndPpoints for customer
	
	@PostMapping("/game_store/{gameStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
    public GameStoreCustomer newStoreCustomer
    (@RequestBody GameStoreCustomer gameStoreCustomer) {
		log.info("Creating customer {} for game store with ID={}");
		return gameStoreService.saveCustomer(gameStoreCustomer);
	}
	
	//EndPoints for game
	
	@PostMapping("/game_store/{gameStoreId}/game")
	@ResponseStatus(code = HttpStatus.CREATED)
    public GameStoreGame newStoreGame
    (@RequestBody GameStoreGame gameStoreGame) {
		log.info("Creating game {} for game store with ID={}");
		return gameStoreService.saveGame(gameStoreGame);
	}
	
	@GetMapping("/game_store/game/{gameId}")
	public GameStoreGame retrieveGameById(@PathVariable Long gameId) {
	log.info("Retrieving game with ID= ", gameId);
	return gameStoreService.retrieveGameById(gameId);
	}
	
	@DeleteMapping("/game/{gameId}")
	public Map<String, String> deleteGameById(@PathVariable Long gameId){
		log.info("Deleting GameStore game with ID= {}", gameId);
		gameStoreService.deleteGameById(gameId);
		return Map.of("message", "deletion is successful " + gameId);
	}
}
