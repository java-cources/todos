package com.todolist.db;

import com.todolist.entity.Task;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class TasksManager {

    @Getter
    public static ArrayList<Task> tasks = new ArrayList<>();

    private static final AtomicLong idGenerator = new AtomicLong();


    static {
        tasks.add(new Task(1L, "Купить хлеб", "2026-03-03", false));
        tasks.add(new Task(2L, "Купить авокадо", "2026-03-03", false));

        long maxId = tasks.stream()
                .mapToLong(Task::getId)
                .max()
                .orElse(0);

        idGenerator.set(maxId);
    }

    public static Task getTaskById(Long id) {
        return tasks.stream().filter(task -> Objects.equals(task.getId(), id)).findFirst().get();
    }

    public static void addTask(Task task) {
        task.setId(idGenerator.incrementAndGet());

        tasks.add(task);
    }

    public static void removeTask(Long id) {
        tasks.removeIf(task -> Objects.equals(task.getId(), id));
    }

    public static void updateTask(Task task) {
        Task find = tasks
                .stream()
                .filter(t -> Objects.equals(t.getId(), task.getId()))
                .findFirst()
                .orElse(null);

        if (find == null) {
            addTask(task);
        } else {
            find.setName(task.getName());
            find.setDeadlineDate(task.getDeadlineDate());
        }
    }

    public static void updateChecked(Long id, Boolean isCompleted) {
        tasks.stream()
                .filter(t -> Objects.equals(t.getId(), id))
                .findFirst()
                .ifPresent(t -> t.setCompleted(isCompleted));
    }
}
