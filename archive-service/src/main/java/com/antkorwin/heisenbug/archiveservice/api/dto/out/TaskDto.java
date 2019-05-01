package com.antkorwin.heisenbug.archiveservice.api.dto.out;

import lombok.*;

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
    private String id;
    private String title;
    private int estimate;
}
