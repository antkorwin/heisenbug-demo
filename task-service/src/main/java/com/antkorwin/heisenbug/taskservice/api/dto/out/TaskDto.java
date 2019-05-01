package com.antkorwin.heisenbug.taskservice.api.dto.out;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private UUID id;
    private String title;
    private int estimate;
}
