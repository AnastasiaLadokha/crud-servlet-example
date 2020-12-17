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
public class Card {

    private int id;
    private Date receipt_date;
    private Date discharge_date;
    private int doctor_id;
    private int patient_id;
    private int disease_id;

}
