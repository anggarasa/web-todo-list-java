package com.anggara.todolistweb.controller;

import com.anggara.todolistweb.model.Task;
import com.anggara.todolistweb.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    // Menampilkan daftar tugas
    @GetMapping("/")
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        model.addAttribute("newTask", new Task());
        return "index";
    }

    // Menambahkan tugas baru dengan validasi
    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute("newTask") Task newTask, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskRepository.findAll());
            return "index";
        }
        taskRepository.save(newTask);
        return "redirect:/";
    }

    // Menandai tugas sebagai selesai
    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable("id") Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setCompleted(!task.isCompleted());
            taskRepository.save(task);
        }
        return "redirect:/";
    }

    // Menampilkan form edit tugas
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable() Long id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("task", taskOptional.get());
        return "edit";
    }

    // Menyimpan perubahan tugas dengan validasi
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable() Long id, @Valid @ModelAttribute("task") Task updatedTask, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", updatedTask);
            return "edit";
        }

        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDescription(updatedTask.getDescription());
            taskRepository.save(task);
        }
        return "redirect:/";
    }

    // Menghapus tugas
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable() Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}
