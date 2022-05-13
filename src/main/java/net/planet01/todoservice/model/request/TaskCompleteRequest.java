package net.planet01.todoservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCompleteRequest {

    private Long id;
    private boolean taskCompleted;

}
