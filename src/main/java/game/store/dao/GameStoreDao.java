package game.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import game.store.entity.GameStore;

public interface GameStoreDao extends JpaRepository<GameStore, Long> {

}
