package net.planet01.todoservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "todo")
@Getter
@Setter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_code")
    private String task;

    @Column(name = "name")
    private String description;

    @Column(name = "is_completed",columnDefinition = "bit default 0")
    private Boolean completed;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "completed_at")
    private LocalDate completedAt;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
