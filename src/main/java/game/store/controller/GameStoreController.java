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

import game.store.controller.model.GameStoreData;
import game.store.controller.model.GameStoreEmployee;
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
	log.info("Retrieving pet store with ID= ", gameStoreId);
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
	
	@DeleteMapping("/employee/{employeeId}")
	public Map<String, String> deleteEmployeeById(@PathVariable Long employeeId){
		log.info("Deleting GameStore employee with ID= {}", employeeId);
		gameStoreService.deleteEmployeeById(employeeId);
		return Map.of("message", "deletion is successful " + employeeId);
	}
}
