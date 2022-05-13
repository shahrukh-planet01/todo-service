package net.planet01.todoservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private String task;
    private String description;
    private Long userId;
    private LocalDate createdAt;
    private LocalDate completedAt;
    private boolean completed;
}
