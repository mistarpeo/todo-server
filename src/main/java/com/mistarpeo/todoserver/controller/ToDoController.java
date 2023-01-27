package com.mistarpeo.todoserver.controller;

import com.mistarpeo.todoserver.model.ToDoEntity;
import com.mistarpeo.todoserver.model.ToDoRequest;
import com.mistarpeo.todoserver.model.ToDoResponse;
import com.mistarpeo.todoserver.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/")
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping
    public ResponseEntity<ToDoResponse> create(@RequestBody ToDoRequest request) {
        System.out.println("CREATE");
        if(ObjectUtils.isEmpty((request.getTitle()))) {
            return ResponseEntity.badRequest().build();
        }

        if(ObjectUtils.isEmpty((request.getOrder()))) {
            request.setOrder(0L);
        }

        if(ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        ToDoEntity result = this.toDoService.add(request);
        return ResponseEntity.ok(new ToDoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoResponse> readOne(@PathVariable Long id) {
        System.out.println("READ ONE");
        ToDoEntity result = this.toDoService.searchById(id);
        return ResponseEntity.ok(new ToDoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<ToDoResponse>> readAll() {
        System.out.println("READ ALL");
        List<ToDoEntity> list = this.toDoService.searchAll();
        List<ToDoResponse> responses = list.stream().map(ToDoResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ToDoResponse> update(@PathVariable Long id, @RequestBody ToDoRequest request) {
        System.out.println("UPDATE");
        ToDoEntity result = this.toDoService.updateById(id, request);
        return ResponseEntity.ok(new ToDoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("DELETE");
        this.toDoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        System.out.println("DELETE ALL");
        this.toDoService.deleteAll();
        return ResponseEntity.ok().build();
    }


}
