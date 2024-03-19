package com.ipsl.taskmanagement.helpers;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponses {
    private int status;
    private String message;
}
