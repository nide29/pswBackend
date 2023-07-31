package it.psw.backend.controllers;

import it.psw.backend.model.Ordine;
import it.psw.backend.model.Utente;
import it.psw.backend.services.OrdineService;
import it.psw.backend.services.UtenteService;
import it.psw.backend.support.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordine")
@Validated
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping
    public ResponseEntity<?> creaOrdine(@RequestBody Ordine request) {
        System.out.println(request);

        Ordine risultato = ordineService.creaOrdine(request);
        if(risultato == null) {
            return new ResponseEntity<>(new ResponseMessage("Errore nella creazione dell'ordine"), HttpStatus.OK);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }//aggiungiOrdine

    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaOrdine(@PathVariable("id") String id, @RequestBody Ordine ordine) {
        if(!ordineService.existById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Ordine non esistente"), HttpStatus.OK);
        }
        Ordine daAggiornare = ordineService.findById(Long.parseLong(id));
        daAggiornare.setAcquirente(ordine.getAcquirente());
        daAggiornare.setImporto(ordine.getImporto());
        daAggiornare.setProdotti(ordine.getProdotti());
        daAggiornare.setDataAcquisto(ordine.getDataAcquisto());
        long idOrdine = ordineService.aggiornaOrdine(daAggiornare).getId();
        return new ResponseEntity<>(idOrdine, HttpStatus.OK);
    }//aggiornaOrdine

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrdine(@PathVariable("id") String id) {
        if(!ordineService.existById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Ordine non esistente"), HttpStatus.OK);
        }
        ordineService.deleteOrdine(Long.parseLong(id));
        return new ResponseEntity<>(Long.parseLong(id), HttpStatus.OK);
    }//deleteOrdine

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Ordine> ordini = ordineService.findAll();
        if(ordini.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(ordini, HttpStatus.OK);
    }//findAll

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        Ordine ordine = ordineService.findById(Long.parseLong(id));
        if(ordine == null) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(ordine, HttpStatus.OK);
    }//findById

    @GetMapping("/{idUtente}/periodo")
    public ResponseEntity<?> findByPeriodo(@PathVariable("idUtente") String idUtente, @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        Utente utente = utenteService.findById(Long.parseLong(idUtente));
        List<Ordine> risultato = ordineService.findByPeriodo(utente, start, end);
        if (risultato.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }//findByPeriodo

    @GetMapping("/acquirente")
    public ResponseEntity<?> findByAcquirente(@RequestParam(value = "email") String email) {
        Utente utente = utenteService.findByEmail(email);
        List<Ordine> ordini = ordineService.findByAcquirente(utente);
        if(ordini.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(ordini, HttpStatus.OK);
    }//findByAcquirente


}//OrdineController
