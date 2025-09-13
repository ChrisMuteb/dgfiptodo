package com.springmvcproject.dgfiptodo.repository;

import com.springmvcproject.dgfiptodo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
