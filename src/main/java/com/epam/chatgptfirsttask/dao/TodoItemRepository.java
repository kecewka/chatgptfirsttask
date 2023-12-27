package com.epam.chatgptfirsttask.dao;

import com.epam.chatgptfirsttask.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
