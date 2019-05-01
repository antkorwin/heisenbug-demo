package com.antkorwin.heisenbug.reportservice.model;

import lombok.*;

import java.util.UUID;

/**
 * Created on 24.09.2018.
 *
 * @author Korovin Anatoliy
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private UUID id;
    private String title;
    private Integer estimate;
}
