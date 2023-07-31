package it.psw.backend.controllers;

import it.psw.backend.support.exceptions.ProdottoEsistenteException;
import it.psw.backend.support.exceptions.ProdottoNotFoundException;
import it.psw.backend.model.Prodotto;
import it.psw.backend.services.ProdottoService;

import java.util.List;

import it.psw.backend.support.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prodotto")
@Validated
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping()
    public ResponseEntity<?> creaProdotto(@Validated @RequestBody Prodotto prodotto) {
        if(prodottoService.existsByNome(prodotto.getNome())) {
            throw new ProdottoEsistenteException("Il prodotto è già esistente");
        }
        prodottoService.save(prodotto);
        return new ResponseEntity<>(prodotto.getId(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateProdotto(@PathVariable("id") String id, @RequestBody Prodotto prodotto) {
        if(!prodottoService.existsById(Long.parseLong(id))) throw new ProdottoNotFoundException("Prodotto non trovato");
        Prodotto daAggiornare = prodottoService.findById(Long.parseLong(id));
        daAggiornare.setNome(prodotto.getNome());
        daAggiornare.setDescrizione(prodotto.getDescrizione());
        daAggiornare.setMarca(prodotto.getMarca());
        daAggiornare.setCategoria(prodotto.getCategoria());
        daAggiornare.setPrezzo(prodotto.getPrezzo());
        long idProdotto = prodottoService.save(daAggiornare);
        return new ResponseEntity<>(idProdotto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProdotto(@PathVariable("id") String id) {
        if(!prodottoService.existsById(Long.parseLong(id))) throw new ProdottoNotFoundException("Prodotto non trovato");
        prodottoService.delete(Long.parseLong(id));
        return new ResponseEntity<>(Long.parseLong(id), HttpStatus.OK);
    }

   @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Prodotto> prodotti = prodottoService.findAll();
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> findById(@PathVariable("id") String id) {
        Prodotto prodotto = prodottoService.findById(Long.parseLong(id));
        return new ResponseEntity<>(prodotto, HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<?> findByNome(@RequestParam("nome") String nome) {
        List<Prodotto> prodotti = prodottoService.findByNome(nome);
        if (prodotti.size() <= 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    @GetMapping("/marca")
    public ResponseEntity<?> findByMarca(@RequestParam("marca") String marca) {
        List<Prodotto> prodotti = prodottoService.findByMarca(marca);
        if (prodotti.size() <= 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public ResponseEntity<?> findByCategoria(@RequestParam("categoria") String categoria) {
        List<Prodotto> prodotti = prodottoService.findByCategoria(categoria);
        if (prodotti.size() <= 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }


}//ProdottoController
