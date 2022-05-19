package net.planet01.todoservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    @NotBlank(message = "task name is mandatory")
    private String task;
    @NotBlank(message = "description is mandatory")
    private String description;
}
