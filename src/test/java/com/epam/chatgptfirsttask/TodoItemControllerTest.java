package com.epam.chatgptfirsttask;

import com.epam.chatgptfirsttask.controller.TodoItemController;
import com.epam.chatgptfirsttask.entity.TodoItem;
import com.epam.chatgptfirsttask.service.TodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoItemControllerTest {

    @Mock
    private TodoItemService todoItemService;

    @InjectMocks
    private TodoItemController todoItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTodoItems_ReturnsTodoItems() {
        List<TodoItem> todoItemList = Collections.emptyList();
        when(todoItemService.getAllTodoItems()).thenReturn(todoItemList);

        List<TodoItem> returnedList = todoItemController.getAllTodoItems();

        assertEquals(0, returnedList.size());
        verify(todoItemService, times(1)).getAllTodoItems();
    }

    @Test
    void getTodoItemById_ExistingId_ReturnsTodoItem() {
        TodoItem mockTodo = new TodoItem();
        mockTodo.setId(1L);
        mockTodo.setTitle("Sample Title");
        mockTodo.setDescription("Sample Description");

        when(todoItemService.getTodoItemById(1L)).thenReturn(Optional.of(mockTodo));

        Optional<TodoItem> returnedTodo = todoItemController.getTodoItemById(1L);

        assertTrue(returnedTodo.isPresent());
        assertEquals("Sample Title", returnedTodo.get().getTitle());
        assertEquals("Sample Description", returnedTodo.get().getDescription());

        verify(todoItemService, times(1)).getTodoItemById(1L);
    }

    @Test
    void createTodoItem_ValidItem_ReturnsCreatedItem() {
        TodoItem newItem = new TodoItem();
        newItem.setTitle("New Todo");
        newItem.setDescription("Description of the new todo");

        when(todoItemService.createTodoItem(newItem)).thenReturn(newItem);

        TodoItem createdItem = todoItemController.createTodoItem(newItem);

        assertEquals("New Todo", createdItem.getTitle());
        assertEquals("Description of the new todo", createdItem.getDescription());

        verify(todoItemService, times(1)).createTodoItem(newItem);
    }

    @Test
    void updateTodoItem_ExistingIdAndValidItem_ReturnsUpdatedItem() {
        TodoItem updatedItem = new TodoItem();
        updatedItem.setId(3L);
        updatedItem.setTitle("Updated Todo");
        updatedItem.setDescription("Updated description of the todo");

        when(todoItemService.updateTodoItem(3L, updatedItem)).thenReturn(updatedItem);

        TodoItem returnedItem = todoItemController.updateTodoItem(3L, updatedItem);

        assertEquals("Updated Todo", returnedItem.getTitle());
        assertEquals("Updated description of the todo", returnedItem.getDescription());

        verify(todoItemService, times(1)).updateTodoItem(3L, updatedItem);
    }

    @Test
    void deleteTodoItem_ExistingId_ReturnsNoContent() {
        todoItemController.deleteTodoItem(5L);

        // Verify that the service method is called with the correct parameter
        verify(todoItemService, times(1)).deleteTodoItem(5L);
    }
}

