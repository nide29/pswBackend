package it.psw.backend.services;

import it.psw.backend.model.Ordine;
import it.psw.backend.model.Prodotto;
import it.psw.backend.model.ProdottoNelCarrello;
import it.psw.backend.model.Utente;
import it.psw.backend.repositories.OrdineRepository;
import it.psw.backend.repositories.ProdottoNelCarrelloRepository;
import it.psw.backend.support.exceptions.DeleteException;
import it.psw.backend.support.exceptions.OrdineNotFoundException;
import it.psw.backend.support.exceptions.QuantitaNonDisponibileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private ProdottoNelCarrelloRepository prodottoNelCarrelloRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Ordine creaOrdine(Ordine ordine) throws QuantitaNonDisponibileException {
        Ordine result = ordineRepository.save(ordine);
        for(ProdottoNelCarrello pnc : ordine.getProdotti()) {
            pnc.setOrdine(result);
            prodottoNelCarrelloRepository.save(pnc);
            Prodotto prodotto = pnc.getProdotto();
            int newQuantity = prodotto.getQuantita() - pnc.getQuantita();
            if (newQuantity < 0) {
                throw new QuantitaNonDisponibileException("Quantità non sufficiente");
            }
            prodotto.setQuantita(newQuantity);
            entityManager.merge(prodotto);
        }
        return result;
    }//creaOrdine

    @Transactional
    public void updateOrdine(Ordine ordine) {
        ordineRepository.save(ordine);
    }//updateOrdine

    @Transactional
    public void deleteOrdine(long idOrdine) {
        try {
            ordineRepository.deleteById(idOrdine);
            ordineRepository.flush();
        } catch (Exception e) {throw new DeleteException("Eliminazione non riuscita!");}
    }//deleteOrdine

    @Transactional(readOnly = true)
    public List<Ordine> findAll() {
        return ordineRepository.findAll();
    }//findAll


    @Transactional(readOnly = true)
    public Ordine findById(long id) {
        if (!ordineRepository.existsById(id)) throw new OrdineNotFoundException("Ordine non esistente");
        if (!ordineRepository.findById(id).isPresent()) throw new OrdineNotFoundException("Ordine non trovato");

        return ordineRepository.findById(id).get();
    }//findById

    @Transactional(readOnly = true)
    public List<Ordine> findByDataAcquisto(String data) {
        return ordineRepository.findByDataAcquisto(data);
    }//findByDAtaAcquisto

    @Transactional(readOnly = true)
    public List<Ordine> findByAcquirente(Utente acquirente) {
        return ordineRepository.findByAcquirente(acquirente);
    }//findByDAtaAcquisto


    @Transactional(readOnly = true)
    public boolean existById(long id){
        return ordineRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existByDataAcquisto(String data){
        return ordineRepository.existsByDataAcquisto(data);
    }

    @Transactional(readOnly = true)
    public boolean existByAcquirente(Utente acquirente){
        return ordineRepository.existsByAcquirente(acquirente);
    }

}//OrdineService
