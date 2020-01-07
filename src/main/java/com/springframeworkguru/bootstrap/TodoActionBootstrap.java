package com.springframeworkguru.bootstrap;

import com.springframeworkguru.domain.TodoAction;
import com.springframeworkguru.repository.TodoActionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TodoActionBootstrap implements CommandLineRunner {
    private final TodoActionRepository todoActionRepository;

    public TodoActionBootstrap(TodoActionRepository todoActionRepository) {
        this.todoActionRepository = todoActionRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        log.debug("DODO: Loading Bootstrap Data");
        TodoAction todoAction1 = new TodoAction();
        todoAction1.setDescription("This is dummy action 2");

        todoActionRepository.save(todoAction1);

        log.debug("DODO: Totoal actions saved: " + todoActionRepository.count());
    }
}
