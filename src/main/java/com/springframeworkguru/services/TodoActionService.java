package com.springframeworkguru.services;

import com.springframeworkguru.commands.TodoActionCommand;
import com.springframeworkguru.domain.TodoAction;

import javax.persistence.Id;
import java.util.Set;

public interface TodoActionService {
    Set<TodoAction> getAllActions();
    TodoAction findById(Long id);
    TodoActionCommand findActionCommandById(Long id);
    TodoActionCommand saveActionCommand(TodoActionCommand todoActionCommand);
    TodoActionCommand editActionCommand(TodoActionCommand todoActionCommand, Long id);
    void deleteAction(Long id);
}
