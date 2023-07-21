package game.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import game.store.entity.Game;

public interface GameDao extends JpaRepository<Game, Long> {

}
