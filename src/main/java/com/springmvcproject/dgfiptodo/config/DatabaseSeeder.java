package com.springmvcproject.dgfiptodo.config;

import com.springmvcproject.dgfiptodo.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);
    private final JdbcTemplate jdbcTemplate;

    public DatabaseSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Seeding task entity");

        try{
            LocalDateTime createdOn = LocalDateTime.now();
            LocalDateTime dueDateOn = LocalDateTime.of(2025, 12, 31, 10, 30);
            Long days = Duration.between(createdOn, dueDateOn).toDays();
            Status st = Status.IN_PROGRESS;
            jdbcTemplate.update(
                    "INSERT INTO task (id, name, description, status, created_on, due_date, duration) VALUES (?,?,?,?,?,?,?)",
                    100L, "test", "testdescription", st.name(), createdOn, dueDateOn, days
            );

        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
