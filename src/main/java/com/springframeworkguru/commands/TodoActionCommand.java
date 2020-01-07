package com.springframeworkguru.commands;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoActionCommand {
    private Long id;
    private String description;
}
