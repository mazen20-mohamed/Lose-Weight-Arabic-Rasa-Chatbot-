package com.example.AuthoRasa;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "weight_update")
public class WeightUpdate {
    @Id
    @GeneratedValue
    private int id;

    private int user_id;

    private Date time_taken;

}
