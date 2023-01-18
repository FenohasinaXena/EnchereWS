package com.example.enchere.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.exeption.RessourceException;
import com.example.enchere.modele.Compte;
import com.example.enchere.modele.Enchere;
import com.example.enchere.modele.Offre;
import com.example.enchere.repository.CompteRepository;
import com.example.enchere.repository.EnchereRepository;
import com.example.enchere.repository.OffreRepository;
import com.example.enchere.retour.ErrorRetour;
import com.example.enchere.retour.SuccessRetour;

@CrossOrigin("*")
@RestController
@RequestMapping("/offres")
public class OffreController {
    
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private OffreRepository offreRepository;



    @GetMapping("/listeOffre")
    public @ResponseBody Map<String, Object> getAllOffre(){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", offreRepository.findAll());
            return data; 
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/insertionOffre")
    public @ResponseBody Map<String, Object> createOffre(@RequestBody Offre offre) throws Exception{
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", offreRepository.save(offre));
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Veuillez vérifier les informations",HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("modifier/{idOffre}")
    public @ResponseBody Map<String, Object> updateOffre(@PathVariable int idOffre,@RequestBody Offre offre) {
        Offre updateOffre = offreRepository.findById(idOffre).orElseThrow(() 
            -> new RessourceException(new ErrorRetour("idOffre : "+idOffre+" n'existe pas",HttpStatus.NO_CONTENT.value()))
        );
        updateOffre.setIdEnchere(offre.getIdEnchere());
        updateOffre.setIdUtilisateur(offre.getIdUtilisateur());
        updateOffre.setPrixOffre(offre.getPrixOffre());
        updateOffre.setDateOffre(offre.getDateOffre());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", offreRepository.save(updateOffre));
        return data;
    }

    @DeleteMapping("delete/{idOffre}")
    public @ResponseBody Map<String, Object> deleteOffre(@PathVariable int idOffre)throws Exception{
        Offre offre = offreRepository.findById(idOffre).orElseThrow(() 
            -> new RessourceException(new ErrorRetour("idOffre : "+idOffre+" n'existe pas",HttpStatus.NOT_FOUND.value()))
        );
        offreRepository.delete(offre);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", new SuccessRetour(" l'idOffre  "+idOffre+" a été supprimé avec succès"));
        return data;
    }


    public void saveOffreUser(Offre max, Offre offre)throws Exception{
        Enchere enchere = enchereRepository.getEnchere(offre.getIdEnchere());
        offre.checkUser(enchere);
        if( max != null ){
            offre.checkMontant(max);
        }
        else{
            offre.checkMontant(enchere);
        }
        Compte userCompte = compteRepository.getCompte(offre.getIdUtilisateur());
        userCompte.checkSolde(offre.getPrixOffre());
        compteRepository.save(userCompte);
        offreRepository.save(offre);
    }

    public void updateLastOffre(Offre max)throws Exception{
        Compte last = compteRepository.getCompte(max.getIdUtilisateur());
        last.setSolde(last.getSolde()+max.getPrixOffre());
        compteRepository.save(last);
    }

    @PostMapping
    public @ResponseBody Map<String, Object> createAvion(@RequestBody Offre offre) throws Exception{
        try{
            Offre max = offreRepository.getOffreMax(offre.getIdEnchere());
            offre.setDateOffre(Timestamp.valueOf(LocalDateTime.now()));
            saveOffreUser(max, offre);
            if( max != null ){
                updateLastOffre(max);
            }
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", new SuccessRetour("Votre offre a été ajouté avec succès"));
            return data;
        }
        catch(Exception e){
            throw e;
        }
    }

   
}
