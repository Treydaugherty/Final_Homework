package game.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import game.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
