package com.antkorwin.heisenbug.archiveservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@Document
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private String id;
    private String title;
    private int estimate;
}
