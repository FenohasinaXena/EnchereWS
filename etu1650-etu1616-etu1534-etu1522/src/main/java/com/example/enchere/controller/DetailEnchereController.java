package com.example.enchere.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.exeption.RessourceException;
import com.example.enchere.modele.DetailEnchere;
import com.example.enchere.repository.DetailEnchereRepository;
import com.example.enchere.retour.ErrorRetour;

@CrossOrigin("*")
@RestController
@RequestMapping("/detailenchere")
public class DetailEnchereController {

    @Autowired
    private DetailEnchereRepository detailRepository;

    @GetMapping("{idenchere}")
    public @ResponseBody Map<String, Object> getDetailEnchereById(@PathVariable int idenchere){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            List<DetailEnchere> liste = detailRepository.getDetailEnchereById(idenchere);
            for(DetailEnchere d : liste ){
                System.out.println("Eto "+d.getIdEnchere());
            }
            data.put("data", detailRepository.getDetailEnchereById(idenchere));
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations", HttpStatus.BAD_REQUEST.value()));
        }
    }
    /*
     @GetMapping("/liste")
    public @ResponseBody Map<String, Object> getAllEnchereVendu(){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", enchereVenduRepository.findAll());
            return data; 
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations", HttpStatus.BAD_REQUEST.value()));
        }
    }
     */
}
