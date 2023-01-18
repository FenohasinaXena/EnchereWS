package com.example.enchere.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.enchere.modele.EnchereVendu;

public interface EnchereVenduRepository extends JpaRepository<EnchereVendu, Integer> {
    
}