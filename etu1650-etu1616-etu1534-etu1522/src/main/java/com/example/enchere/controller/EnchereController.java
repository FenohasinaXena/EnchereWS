package com.example.enchere.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.exeption.RessourceException;
import com.example.enchere.modele.Condition;
import com.example.enchere.modele.Enchere;
import com.example.enchere.modele.ImageEnchere;
import com.example.enchere.modele.Status;
import com.example.enchere.modele.V_Enchere;
import com.example.enchere.repository.EnchereRepository;
import com.example.enchere.repository.HistoriqueEnchereRepository;
import com.example.enchere.repository.ImageEnchereRepository;
import com.example.enchere.repository.V_EnchereRepository;
import com.example.enchere.retour.ErrorRetour;
import com.example.enchere.retour.SuccessRetour;

@CrossOrigin("*")
@RestController
@RequestMapping("/encheres")
public class EnchereController {

    @Autowired
    private HistoriqueEnchereRepository historiqueEnchereRepository;

    @Autowired
    private V_EnchereRepository v_enchereRepository;

    @Autowired
    private ImageEnchereRepository imageEnchereRepository;

    @Autowired
    private EnchereRepository enchereRepository;


    V_Enchere v = new V_Enchere();

    Status [] status = {
        new Status(1,"Non demarrer"),
        new Status(2,"En Cours"),
        new Status(3,"Vendu"),
        new Status(4,"Non-Vendu")
    };

    public void insertionImage(Enchere enchere, ImageEnchere [] images){
        try{
            for(int i=0; i<images.length; i++){
                images[i].setIdEnchere(enchere.getIdEnchere());
                imageEnchereRepository.save(images[i]);
            }
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour("Insertion Image : "+e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/recherches")
    public @ResponseBody Map<String, Object> recherches(@RequestBody Condition condition ){
        try{
            String c = condition.conditionRequete();
            Map<String, Object> data = new HashMap<String, Object>();
            List <V_Enchere> liste = v.getAll(c);
            data.put("data", liste);
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("{idEnchere}")
    public @ResponseBody Map<String, Object> historiques(@PathVariable int idEnchere){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data",historiqueEnchereRepository.getHistoriques(idEnchere));
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/listeEnchere/{idUtilisateur}")
    public @ResponseBody Map<String, Object> listeEnchere(@PathVariable int idUtilisateur){
        try{
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data",v_enchereRepository.getAll(idUtilisateur));
            return data;
        }
        catch(Exception e){
            throw new RessourceException(new ErrorRetour(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

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



