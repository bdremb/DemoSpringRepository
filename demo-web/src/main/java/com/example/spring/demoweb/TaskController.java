package com.example.spring.demoweb;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Controller
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", tasks);
        return "index"; //index.html
    }

    @GetMapping("/task/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "create"; //create.html
    }

    @PostMapping("/task/create")
    public String createTask(@ModelAttribute Task task) {
        task.setId(System.currentTimeMillis());
        tasks.add(task);

        return "redirect:/";  //public String index(Model model)
    }

    @GetMapping("/task/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = getTaskById(id);
        if (nonNull(task)) {
            model.addAttribute("task", task);
            return "edit"; //edit.html
        }
        return "redirect:/"; //public String index(Model model)
    }

    @PostMapping("/task/edit")
    public String editTask(@ModelAttribute Task task) {
        Task existingTask = getTaskById(task.getId());
        if (nonNull(existingTask)) {
            existingTask.setDescription(task.getDescription());
            existingTask.setPriority(task.getPriority());
            existingTask.setTitle(task.getTitle());
        }
        return "redirect:/"; //public String index(Model model)
    }

    @GetMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = getTaskById(id);
        if (nonNull(task)) {
           tasks.remove(task);
        }
        return "redirect:/"; //public String index(Model model)
    }

    private Task getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
