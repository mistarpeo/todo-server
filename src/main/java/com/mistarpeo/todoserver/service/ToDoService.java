package com.mistarpeo.todoserver.service;

import com.mistarpeo.todoserver.model.ToDoEntity;
import com.mistarpeo.todoserver.model.ToDoRequest;
import com.mistarpeo.todoserver.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ToDoService {

    public final ToDoRepository toDoRepository;

    public ToDoEntity add(ToDoRequest request) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setTitle(request.getTitle());
        toDoEntity.setOrder(request.getOrder());
        toDoEntity.setCompleted(request.getCompleted());
        return this.toDoRepository.save(toDoEntity);
    }

    public ToDoEntity searchById(Long id) {
        return this.toDoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<ToDoEntity> searchAll() {
        return this.toDoRepository.findAll();
    }

    public ToDoEntity updateById(Long id, ToDoRequest request) {
        ToDoEntity toDoEntity = this.searchById(id);

        if(request.getTitle() != null) {
            toDoEntity.setTitle(request.getTitle());
        }
        if(request.getOrder() != null) {
            toDoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null) {
            toDoEntity.setCompleted(request.getCompleted());
        }
        return this.toDoRepository.save(toDoEntity);
    }

    public void deleteById(Long id) {
        this.toDoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.toDoRepository.deleteAll();
    }
}
