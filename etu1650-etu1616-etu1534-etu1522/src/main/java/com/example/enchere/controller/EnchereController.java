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
import com.example.enchere.modele.Enchere;
import com.example.enchere.repository.EnchereRepository;
import com.example.enchere.retour.ErrorRetour;
import com.example.enchere.retour.SuccessRetour;

@RestController
@RequestMapping("/enchere")
public class EnchereController {

    @Autowired
    private EnchereRepository enchereRepository;

    @GetMapping("/listeEnchere")
    public @ResponseBody Map<String, Object> getAllEnchere(){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", enchereRepository.findAll());
            return data; 
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/insertionEnchere")
    public @ResponseBody Map<String, Object> createEnchere(@RequestBody Enchere enchere) throws Exception{
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", enchereRepository.save(enchere));
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations",HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("modifier/{idEnchere}")
    public @ResponseBody Map<String, Object> updateEnchere(@PathVariable int idEnchere,@RequestBody Enchere enchere) {
        Enchere updateEnchere = enchereRepository.findById(idEnchere).orElseThrow(() 
            -> new RessourceException(new ErrorRetour("idEnchere : "+idEnchere+" n'existe pas",HttpStatus.NO_CONTENT.value()))
        );
        updateEnchere.setNom(enchere.getNom());
        updateEnchere.setDescriptions(enchere.getDescriptions());
        updateEnchere.setPrixEnchere(enchere.getPrixEnchere());
        updateEnchere.setIdUtilisateur(enchere.getIdUtilisateur());
        updateEnchere.setIdCommission(enchere.getIdCommission());
        updateEnchere.setIdCategorie(enchere.getIdCategorie());
        updateEnchere.setDateEnchere(enchere.getDateEnchere());
        updateEnchere.setDuree(enchere.getDuree());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", enchereRepository.save(updateEnchere));
        return data;
    }

    @DeleteMapping("delete/{idEnchere}")
    public @ResponseBody Map<String, Object> deleteEnchere(@PathVariable int idEnchere)throws Exception{
        Enchere enchere = enchereRepository.findById(idEnchere).orElseThrow(() 
            -> new RessourceException(new ErrorRetour("idEnchere : "+idEnchere+" n'existe pas",HttpStatus.NOT_FOUND.value()))
        );
        enchereRepository.delete(enchere);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", new SuccessRetour(" l'idEnchere  "+idEnchere+" a été supprimé avec succès"));
        return data;
    }
}
