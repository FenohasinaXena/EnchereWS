package com.example.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.enchere.modele.Offre;
import com.example.enchere.modele.V_Enchere;

public interface V_EnchereRepository extends JpaRepository<V_Enchere, Integer> {
    

}