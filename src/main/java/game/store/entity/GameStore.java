package game.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class GameStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameStoreId;
	private String gameStoreName;
	private String address;
	private String phoneNumber;
	private String city;
	private String state;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "game_store_customer", joinColumns = 
	@JoinColumn(name = "game_store_id"), inverseJoinColumns = @JoinColumn
	(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "gameStore", cascade = CascadeType.ALL)
	private Set<Employee> employees = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "game_store_game", joinColumns = 
	@JoinColumn(name = "game_store_id"), inverseJoinColumns = @JoinColumn
	(name = "game_id"))
	private Set<Game> games = new HashSet<>();

}
