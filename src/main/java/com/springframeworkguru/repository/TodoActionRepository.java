package com.springframeworkguru.repository;

import com.springframeworkguru.domain.TodoAction;
import org.springframework.data.repository.CrudRepository;

public interface TodoActionRepository extends CrudRepository<TodoAction, Long> {
}
