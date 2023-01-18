package com.example.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.enchere.modele.Enchere;

public interface EnchereRepository extends JpaRepository<Enchere, Integer> {
    
    @Query(value="SELECT * FROM Enchere WHERE idEnchere = ?1",nativeQuery = true)
    public Enchere getEnchere(int idEnchere);
}