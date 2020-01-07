package com.springframeworkguru.controllers;

import com.springframeworkguru.commands.TodoActionCommand;
import com.springframeworkguru.domain.TodoAction;
import com.springframeworkguru.dummy.Welcome;
import com.springframeworkguru.mappers.TodoActionMapper;
import com.springframeworkguru.services.TodoActionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class ActionControllers {
    private final TodoActionServiceImpl todoActionService;
    private final TodoActionMapper todoActionMapper;


    public ActionControllers(TodoActionServiceImpl todoActionService, TodoActionMapper todoActionMapper) {
        this.todoActionService = todoActionService;
        this.todoActionMapper = todoActionMapper;
    }

    @RequestMapping({"", "/", "/index", "index"})
    public String serveIndexPage(Model model) {
        log.debug("DODO: Loading index page");

        Welcome welcome = new Welcome();
        welcome.setMessage("Welcome To ToDo App");

        model.addAttribute("welcome", welcome);
        return "index";
    }

    @RequestMapping("/allActions")
    public String serveAllActionsPage(Model model) {
        log.debug("DODO: Loading All Actions Page");

        model.addAttribute("actions", todoActionService.getAllActions());

        return "todo/viewall";
    }

    @RequestMapping("/new")
    public String serverNewItemPage(Model model) {
        log.debug("DODO: Loading new action item page");
        model.addAttribute("todoaction", new TodoActionCommand());

        return "todo/newaction";
    }

    @PostMapping("/new")
    public String saveOrUpdate(@Valid @ModelAttribute("todoaction") TodoActionCommand command, BindingResult bindingResult){
        log.debug("DODO: Post method, new action");
        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug("DODO: errors: " + objectError.toString());
            });

            return "todo/new";
        }

        TodoActionCommand savedCommand = todoActionService.saveActionCommand(command);

        //return "redirect:/recipe/" + savedCommand.getId() + "/show";
        return "redirect:/allActions";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @Valid @ModelAttribute("actionCommand") TodoActionCommand command, BindingResult bindingResult) {
        log.debug("DODO: Update Object Received = " + command.getId() + " : " + command.getDescription());

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug("DODO: errors: " + objectError.toString());
            });

            return "redirect:/allActions";
        }

        todoActionService.editActionCommand(command, new Long(id));

        return "redirect:/allActions";
    }


    @RequestMapping("/delete/{id}")
    public String serverNewItemPage(@PathVariable String id, Model model) {
        log.debug("DODO: Deleting action with id: " + id);

        todoActionService.deleteAction(new Long(id));

        return "redirect:/allActions";
    }

    @RequestMapping("/edit/{id}")
    public String editAction(@PathVariable String id, Model model) {
        log.debug("DODO: Edit action, rendering edit page");
        log.debug("DODO: Id received: " + id);

        TodoActionCommand todoActionCommand = new TodoActionCommand();
        todoActionCommand.setId(new Long(id));

        TodoAction existingObject = todoActionService.findById(new Long(id));
        todoActionCommand.setDescription(existingObject.getDescription());

        model.addAttribute("actionCommand", todoActionCommand);
        model.addAttribute("existingObject", todoActionMapper.todoActionToCommand(existingObject));

        return "todo/update";
    }

}
