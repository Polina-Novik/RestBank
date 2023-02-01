package by.novik.restbank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "animals")

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "is_flyable")
    private boolean isFlyable;

    @Column(name = "legs_number")
    private int legsNumber;

}
