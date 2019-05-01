package com.antkorwin.heisenbug.archiveservice.repository;

import com.antkorwin.heisenbug.archiveservice.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
public interface TaskRepository extends MongoRepository<Task, String> {
}
