package com.example.enchere.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.exeption.RessourceException;
import com.example.enchere.modele.Compte;
import com.example.enchere.repository.CompteRepository;
import com.example.enchere.retour.ErrorRetour;
import com.example.enchere.retour.SuccessRetour;

@RestController
@RequestMapping("/compte")
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;

   // idcompte | idutilisateur |  numero  | datecompte | solde

   @PutMapping("modifier/{idUtilisateur}")
    public @ResponseBody Map<String, Object> updateEnchere(@PathVariable int idCompte,@RequestBody Compte compte) {
        Compte updateCompte = compteRepository.findById(idCompte).orElseThrow(() 
            -> new RessourceException(new ErrorRetour("idUtilisateur : "+idCompte+" n'existe pas",HttpStatus.NO_CONTENT.value()))
        );
        updateCompte.setIdCompte(compte.getIdCompte());
        updateCompte.setIdUtilisateur(compte.getIdUtilisateur());
        updateCompte.setNumero(compte.getNumero());
        updateCompte.setDate(compte.getDate());
        updateCompte.setSolde(compte.getSolde());
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", compteRepository.save(updateCompte));
        return data;
    }
}
