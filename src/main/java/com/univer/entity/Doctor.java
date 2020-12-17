package com.univer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    private int id;
    private String first_name;
    private String last_name;
    private String position;
    private int department_id;
    private Date date_of_birth;

}
