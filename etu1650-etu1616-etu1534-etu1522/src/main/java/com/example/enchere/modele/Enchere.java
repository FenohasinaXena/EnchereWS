package com.example.enchere.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enchere")

public class Enchere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenchere")
    private int idEnchere;

    @Column(name = "nom")
    private String nom;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "prixenchere")
    private double prixEnchere;

    @Column(name = "prixmin")
    private double prixMin;

    @Column(name = "idutilisateur")
    private int idUtilisateur;

    @Column(name = "idcommission")
    private int idCommission;

    @Column(name = "idcategorie")
    private int idCategorie;

    @Column(name = "dateenchere")
    private Date dateEnchere;

    @Column(name = "duree")
    private Time duree;

}
