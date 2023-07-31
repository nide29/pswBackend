package it.psw.backend.controllers;


import it.psw.backend.model.Utente;
import it.psw.backend.services.UtenteService;
import it.psw.backend.support.ResponseMessage;
import it.psw.backend.support.exceptions.UtenteEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/utente")
@Validated
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }


    @PostMapping
    public ResponseEntity<?> creaUtente(@RequestBody @Valid Utente utente) {
        if(utenteService.existsByEmail(utente.getEmail())) {
            throw new UtenteEsistenteException("L'utente è già esistente!");
        }
        Utente risultato = utenteService.creaUtente(utente);
        return new ResponseEntity<>(risultato.getId(), HttpStatus.OK);
    }//creaUtente

    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaUtente(@PathVariable("id") String id, @RequestBody Utente utente) {
        if(!utenteService.existsByEmail(utente.getEmail())) {
            throw new UtenteEsistenteException("L'utente non esiste!");
        }
        Utente daAggiornare = utenteService.findById(Long.parseLong(id));
        daAggiornare.setEmail(utente.getEmail());
        daAggiornare.setNome(utente.getNome());
        daAggiornare.setCognome(utente.getCognome());
        daAggiornare.setPassword(utente.getPassword());
        utenteService.aggiornaUtente(daAggiornare);
        return new ResponseEntity<>(daAggiornare.getId(), HttpStatus.OK);
    }//aggiornaUtente

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancellaUtente(@PathVariable("id") String id) {
        if(!utenteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        utenteService.cancellaUtente(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }//cancellaUtente

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Utente> utenti = utenteService.findAll();
        if(utenti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }//findAll

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        Utente utente = utenteService.findById(Long.parseLong(id));
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }//findALl

    @GetMapping("/email")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        Utente utente = utenteService.findByEmail(email);
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }//findByEmail

    @GetMapping("/nome")
    public ResponseEntity<?> findByNome(@RequestParam("nome") String nome) {
        List<Utente> utenti = utenteService.findByNome(nome);
        if(utenti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }//findByNome

    @GetMapping("/cognome")
    public ResponseEntity<?> findByCognome(@RequestParam("cognome") String cognome) {
        List<Utente> utenti = utenteService.findByCognome(cognome);
        if(utenti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }//findByCognome


}//UtenteController
