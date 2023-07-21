package game.store.controller.model;

import game.store.entity.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameStoreGame {

	private Long gameId;
	private String gameName;
	private String gamePrice;
	private String gameConsole;
	private String numberOfCopies;
	public GameStoreGame(Game game) {
		gameId = game.getGameId();
		gameName = game.getGameName();
		gamePrice = game.getGamePrice();
		gameConsole = game.getGameConsole();
		numberOfCopies = game.getNumberOfCopies();
	}
}
