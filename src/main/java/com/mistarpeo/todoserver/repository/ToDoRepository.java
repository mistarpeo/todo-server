package com.mistarpeo.todoserver.repository;

import com.mistarpeo.todoserver.model.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {
}
