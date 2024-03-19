package com.ipsl.taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    private Date due_date;
}
