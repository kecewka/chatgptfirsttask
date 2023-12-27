package com.epam.chatgptfirsttask.controller;

import com.epam.chatgptfirsttask.entity.TodoItem;
import com.epam.chatgptfirsttask.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return todoItemService.getAllTodoItems();
    }

    @GetMapping("/{id}")
    public Optional<TodoItem> getTodoItemById(@PathVariable Long id) {
        return todoItemService.getTodoItemById(id);
    }

    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        return todoItemService.createTodoItem(todoItem);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodoItem(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        return todoItemService.updateTodoItem(id, todoItem);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
    }
}

