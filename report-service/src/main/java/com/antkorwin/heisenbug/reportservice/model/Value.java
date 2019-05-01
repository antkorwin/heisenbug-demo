package com.antkorwin.heisenbug.reportservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on 24.09.2018.
 *
 * @author Korovin Anatoliy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Value {

    private String name;
    private Double value;
}
