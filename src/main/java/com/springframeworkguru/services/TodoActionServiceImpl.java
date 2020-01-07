package com.springframeworkguru.services;

import com.springframeworkguru.commands.TodoActionCommand;
import com.springframeworkguru.domain.TodoAction;
import com.springframeworkguru.mappers.TodoActionMapper;
import com.springframeworkguru.repository.TodoActionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class TodoActionServiceImpl implements TodoActionService {
    private final TodoActionRepository todoActionRepository;
    private final TodoActionMapper todoActionMapper;

    public TodoActionServiceImpl(TodoActionRepository todoActionRepository, TodoActionMapper todoActionMapper) {
        this.todoActionRepository = todoActionRepository;
        this.todoActionMapper = todoActionMapper;
    }


    @Override
    public Set<TodoAction> getAllActions() {
        log.debug("DODO: Inside Get All Action Items method");
        Set<TodoAction> todoActions = new HashSet<>();

        todoActionRepository.findAll().iterator().forEachRemaining(todoActions::add);

        return todoActions;
    }

    @Override
    public TodoAction findById(Long id) {
        log.debug("DODO: Inside Find By Id method");
        Optional<TodoAction> todoAction = todoActionRepository.findById(id);

        if(todoAction.isPresent()) {
            return todoAction.get();
        }
        return null;
    }

    @Override
    public TodoActionCommand findActionCommandById(Long id) {
        log.debug("DODO: Inside Find Command By Id method");
        return todoActionMapper.todoActionToCommand(findById(id));
    }

    @Override
    public TodoActionCommand saveActionCommand(TodoActionCommand todoActionCommand) {
        log.debug("DODO: Inside Save Command method");
        //Convert command to entity object
        TodoAction todoAction = todoActionMapper.todoActionCommandToObject(todoActionCommand);

        //save entity object
        TodoAction todoActionSaved = todoActionRepository.save(todoAction);

        //convert saved entiry object and return
        return todoActionMapper.todoActionToCommand(todoActionSaved);
    }

    @Override
    public TodoActionCommand editActionCommand(TodoActionCommand todoActionCommand, Long id) {
        log.debug("DODO: Inside update command method");

        TodoActionCommand fromDb = findActionCommandById(id);
        fromDb.setDescription(todoActionCommand.getDescription());
        TodoAction actionSaved = todoActionRepository.save(todoActionMapper.todoActionCommandToObject(fromDb));

        return todoActionMapper.todoActionToCommand(actionSaved);
    }

    @Override
    public void deleteAction(Long id) {
        log.debug("DODO: Inside Delete method");
        todoActionRepository.deleteById(id);
    }
}
