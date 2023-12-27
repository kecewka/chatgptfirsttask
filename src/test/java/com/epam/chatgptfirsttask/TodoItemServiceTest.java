package com.epam.chatgptfirsttask;

import com.epam.chatgptfirsttask.dao.TodoItemRepository;
import com.epam.chatgptfirsttask.entity.TodoItem;
import com.epam.chatgptfirsttask.service.TodoItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoItemServiceTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemService todoItemService;

    public TodoItemServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTodoItems_ReturnsTodoItems() {
        // Mock repository behavior
        when(todoItemRepository.findAll()).thenReturn(Collections.emptyList());

        // Call service method
        assertEquals(0, todoItemService.getAllTodoItems().size());

        // Verify repository method invocation
        verify(todoItemRepository, times(1)).findAll();
    }

    // Add similar tests for other service methods (getTodoItemById, createTodoItem, updateTodoItem, deleteTodoItem)
    @Test
    void getTodoItemById_ExistingId_ReturnsTodoItem() {
        TodoItem mockTodo = new TodoItem();
        mockTodo.setId(1L);
        mockTodo.setTitle("Sample Title");
        mockTodo.setDescription("Sample Description");

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(mockTodo));

        Optional<TodoItem> result = todoItemService.getTodoItemById(1L);
        assertTrue(result.isPresent());
        assertEquals("Sample Title", result.get().getTitle());
        assertEquals("Sample Description", result.get().getDescription());

        verify(todoItemRepository, times(1)).findById(1L);
    }

    @Test
    void getTodoItemById_NonExistingId_ReturnsEmptyOptional() {
        when(todoItemRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<TodoItem> result = todoItemService.getTodoItemById(2L);
        assertFalse(result.isPresent());

        verify(todoItemRepository, times(1)).findById(2L);
    }

    @Test
    void createTodoItem_ValidItem_ReturnsCreatedItem() {
        TodoItem newItem = new TodoItem();
        newItem.setTitle("New Todo");
        newItem.setDescription("Description of the new todo");

        when(todoItemRepository.save(newItem)).thenReturn(newItem);

        TodoItem createdItem = todoItemService.createTodoItem(newItem);
        assertNotNull(createdItem);
        assertEquals("New Todo", createdItem.getTitle());
        assertEquals("Description of the new todo", createdItem.getDescription());

        verify(todoItemRepository, times(1)).save(newItem);
    }

    @Test
    void updateTodoItem_ExistingIdAndValidItem_ReturnsUpdatedItem() {
        TodoItem existingItem = new TodoItem();
        existingItem.setId(3L);
        existingItem.setTitle("Existing Todo");
        existingItem.setDescription("Description of the existing todo");

        TodoItem updatedItem = new TodoItem();
        updatedItem.setId(3L);
        updatedItem.setTitle("Updated Todo");
        updatedItem.setDescription("Updated description of the todo");

        when(todoItemRepository.existsById(3L)).thenReturn(true);
        when(todoItemRepository.save(updatedItem)).thenReturn(updatedItem);

        TodoItem result = todoItemService.updateTodoItem(3L, updatedItem);
        assertNotNull(result);
        assertEquals("Updated Todo", result.getTitle());
        assertEquals("Updated description of the todo", result.getDescription());

        verify(todoItemRepository, times(1)).save(updatedItem);
    }

    @Test
    void updateTodoItem_NonExistingId_ReturnsNull() {
        TodoItem updatedItem = new TodoItem();
        updatedItem.setId(4L);
        updatedItem.setTitle("Updated Todo");
        updatedItem.setDescription("Updated description of the todo");

        when(todoItemRepository.existsById(4L)).thenReturn(false);

        TodoItem result = todoItemService.updateTodoItem(4L, updatedItem);
        assertNull(result);

        verify(todoItemRepository, never()).save(updatedItem);
    }

    @Test
    void deleteTodoItem_ExistingId_DeletesItem() {
        doNothing().when(todoItemRepository).deleteById(5L);

        todoItemService.deleteTodoItem(5L);

        verify(todoItemRepository, times(1)).deleteById(5L);
    }
}
