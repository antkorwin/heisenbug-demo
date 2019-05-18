package com.antkorwin.heisenbug.taskservice.repository;

import com.antkorwin.heisenbug.taskservice.model.Task;
import com.jupiter.tools.spring.test.jpa.extension.TraceSqlExtension;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresDataTest;
import com.jupiter.tools.spring.test.postgres.extension.PostgresTcExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 13.05.2019.
 *
 * @author Korovin Anatoliy
 */
//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//@ExtendWith(PostgresTcExtension.class)
//@ExtendWith(TraceSqlExtension.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnablePostgresDataTest
class TaskRepositoryIT {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @Sql("/sql/trgm.sql")
    @Sql("/sql/populate_old_way.sql")
    void findByTitle() {
        // Arrange
        // Act
        Task task = taskRepository.getTopSimilarity("bark");
        // Asserts
        assertThat(task).isNotNull()
                        .extracting(Task::getTitle)
                        .isEqualTo("turn to the dark side");
    }
}