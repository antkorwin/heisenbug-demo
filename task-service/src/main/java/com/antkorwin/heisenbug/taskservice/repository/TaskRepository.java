package com.antkorwin.heisenbug.taskservice.repository;

import java.util.UUID;

import com.antkorwin.heisenbug.taskservice.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = "select * from task ORDER BY similarity(title, ?1) DESC LIMIT 1",
           nativeQuery = true)
    Task getTopSimilarity(String title);
}
