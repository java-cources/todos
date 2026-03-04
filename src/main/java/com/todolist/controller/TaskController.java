package com.todolist.controller;

import com.todolist.db.TasksManager;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @GetMapping("/")
    public String getTodosPage(Model model) {
        model.addAttribute("todos", TasksManager.getTasks());

        return "index";
    }
}
