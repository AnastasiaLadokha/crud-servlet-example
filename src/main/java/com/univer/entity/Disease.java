package com.univer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
    private int id;
    private String title;
    private String treatment;
    private String prevention;

}
