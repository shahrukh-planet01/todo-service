package net.planet01.todoservice.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TodoResponse {
    private String task;
    private String description;
    private LocalDate createdAt;
    private Boolean completed;
    private LocalDate completedAt;
}
