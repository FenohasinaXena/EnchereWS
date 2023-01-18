package com.example.enchere.modele;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detailenchere")
public class DetailEnchere {
    
    @Id
    @Column(name = "idenchere")
    private int idEnchere;

    @Column(name = "nom")
    private String nom;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "prixenchere")
    private double prixEnchere;

    @Column(name = "dateenchere")
    private Date dateEnchere;

    @Column(name = "duree")
    private Time duree;

    @Column(name = "taux")
    private double taux;

    @Column(name = "statusenchere")
    private String statusEnchere;

    @Column(name = "nomvendeur")
    private String nomVendeur;

    @Column(name = "prenomvendeur")
    private String prenomVendeur;

    @Column(name = "idimageenchere")
    private String idImageEnchere;

    @Column(name = "nomimage")
    private String nomImage;

    @Column(name = "format")
    private String format;


}
