package com.ada.SpaPetProjeto.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PETS")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "genero")
    private String gender;

    @Column(name = "raca")
    private String race;

    @Column(name = "peso", nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Customer owner;

}

