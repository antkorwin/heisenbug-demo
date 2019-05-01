package com.antkorwin.heisenbug.archiveservice.event;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;

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