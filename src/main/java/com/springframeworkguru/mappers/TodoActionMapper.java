package com.springframeworkguru.mappers;

import com.springframeworkguru.commands.TodoActionCommand;
import com.springframeworkguru.domain.TodoAction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface TodoActionMapper {
    TodoActionMapper INSTANCE = Mappers.getMapper(TodoActionMapper.class);

    TodoActionCommand todoActionToCommand(TodoAction todoAction);
    TodoAction todoActionCommandToObject(TodoActionCommand todoActionCommand);
}
