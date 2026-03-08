package com.todolist.controller;

import com.todolist.db.TasksManager;
import com.todolist.entity.Task;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    @GetMapping("/")
    public String getTodosPage(Model model) {
        model.addAttribute("todos", TasksManager.getTasks());

        return "index";
    }

    @PostMapping("/add-todo")
    public String postTodoPage(Task task) {
        TasksManager.addTask(task);

        return "redirect:/";
    }

    @PostMapping("/update-checked")
    public String updateCheckedPage(
            @RequestParam Long id,
            @RequestParam(required = false) Boolean completed
    ) {
        boolean isCompleted = completed != null;
        TasksManager.updateChecked(id, isCompleted);

        return "redirect:/";
    }
}
