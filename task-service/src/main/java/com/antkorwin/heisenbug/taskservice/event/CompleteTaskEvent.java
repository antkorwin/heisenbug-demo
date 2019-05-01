package com.antkorwin.heisenbug.taskservice.event;

import java.io.Serializable;
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
public class CompleteTaskEvent implements Serializable {

    private UUID id;
    private String title;
    private int estimate;
}
