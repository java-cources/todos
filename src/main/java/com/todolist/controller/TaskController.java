package com.todolist.controller;

import com.todolist.db.TasksManager;
import com.todolist.entity.Task;
import jakarta.websocket.server.PathParam;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @GetMapping("/")
    public String getTodosPage(Model model) {
        model.addAttribute("todos", TasksManager.getTasks());

        return "index";
    }

    @GetMapping("/details/{id}")
    public String getTaskDetails(@PathVariable Long id, Model model) {
        model.addAttribute("todo", TasksManager.getTaskById(id));
        return "todo_details";
    }

    @PostMapping("/add-todo")
    public String postTodoPage(Task task) {
        TasksManager.addTask(task);

        return "redirect:/";
    }

    @PostMapping("/todo-delete")
    public String deleteTodoPage(Long id) {
        System.out.println("id: " + id);
        TasksManager.removeTask(id);

        return "redirect:/";
    }

    @PostMapping("/todo-update")
    public String updateTodo(Task task) {
        TasksManager.updateTask(task);

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
