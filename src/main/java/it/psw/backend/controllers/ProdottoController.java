package it.psw.backend.controllers;

import it.psw.backend.exceptions.ProdottoEsistenteException;
import it.psw.backend.exceptions.ProdottoNotFoundException;
import it.psw.backend.model.Prodotto;
import it.psw.backend.services.ProdottoService;

import java.util.List;

import it.psw.backend.support.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping()
    public ResponseEntity<?> creaProdotto(Prodotto prodotto) {
        if(prodottoService.existsByNome(prodotto.getNome())) {
            throw new ProdottoEsistenteException("Il prodotto è già esistente");
        }
        prodottoService.save(prodotto);
        return new ResponseEntity<>(prodotto.getId(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateProdotto(@PathVariable("id") String id, Prodotto prodotto) {
        if(!prodottoService.existsById(Long.parseLong(id))) throw new ProdottoNotFoundException("Prodotto non trovato");
        long idProdotto = prodottoService.save(prodotto);
        return new ResponseEntity<>(idProdotto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProdotto(@PathVariable("id") String id) {
        if(!prodottoService.existsById(Long.parseLong(id))) throw new ProdottoNotFoundException("Prodotto non trovato");
        prodottoService.delete(Long.parseLong(id));
        return new ResponseEntity<>(Long.parseLong(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable("nome") String nome) {
        List<Prodotto> prodotti = prodottoService.findByNome(nome);
        if (prodotti.size() <= 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<?> findByMarca(@PathVariable("marca") String marca) {
        List<Prodotto> prodotti = prodottoService.findByMarca(marca);
        if (prodotti.size() <= 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> findByCategoria(@PathVariable("categoria") String categoria) {
        List<Prodotto> prodotti = prodottoService.findByCategoria(categoria);
        if (prodotti.size() <= 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }


}//ProdottoController
