package com.example.enchere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.enchere.modele.DetailEnchere;

public interface DetailEnchereRepository extends JpaRepository<DetailEnchere, Integer>{
    @Query(value = "SELECT * FROM DetailEnchere d WHERE d.idEnchere = ?", nativeQuery = true)
    public List<DetailEnchere> getDetailEnchereById(int idEnchere);
}
