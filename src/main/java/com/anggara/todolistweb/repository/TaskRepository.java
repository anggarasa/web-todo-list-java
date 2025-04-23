package com.anggara.todolistweb.repository;

import com.anggara.todolistweb.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
