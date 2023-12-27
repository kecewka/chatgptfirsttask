package com.epam.chatgptfirsttask.service;

import com.epam.chatgptfirsttask.dao.TodoItemRepository;
import com.epam.chatgptfirsttask.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public List<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> getTodoItemById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem createTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public TodoItem updateTodoItem(Long id, TodoItem todoItem) {
        if (todoItemRepository.existsById(id)) {
            todoItem.setId(id);
            return todoItemRepository.save(todoItem);
        }
        return null;
    }

    public void deleteTodoItem(Long id) {
        todoItemRepository.deleteById(id);
    }
}
