package com.tdevs.dockermastery.repositories;
import com.tdevs.dockermastery.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    // Additional query methods can be defined here
}
