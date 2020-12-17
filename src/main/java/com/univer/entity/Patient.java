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
public class Patient {

    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private String phone_number;
    private Date date_of_birth;
    private String status;
}
