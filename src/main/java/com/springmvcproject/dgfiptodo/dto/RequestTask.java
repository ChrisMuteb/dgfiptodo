package com.springmvcproject.dgfiptodo.dto;

import jakarta.persistence.Column;

public record RequestTask(
        String name,
        String description,
        String status,
        String dueDate
) {
}
