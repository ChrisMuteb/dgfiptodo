package com.springmvcproject.dgfiptodo.dto;

public record ResponseTask(
        String id,
        String name,
        String description,
        String status,
        String dueDate
) {
}
