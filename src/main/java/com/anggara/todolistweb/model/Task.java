package com.anggara.todolistweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Deskripsi tugas tidak boleh kosong")
    @Size(min = 3, message = "Deskripsi tugas minimal 3 karakter")
    private String description;
    private boolean completed;

    // Constructor
    public Task() {
    }
    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

